package com.yt.http.thread;

import com.yt.http.core.HttpSample;
import com.yt.http.listener.IResultListener;
import com.yt.http.request.HttpClientConfig;
import com.yt.utils.StrKit;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class JThreadGroup implements Runnable{

    private final Map<String, Object> propMap =
            Collections.synchronizedMap(new LinkedHashMap<String, Object>());

    public static final String LOOP = "JThreadGroup.Loop";

    public static final String RAMP_UP = "JThreadGroup.Rampup";

    public static final String Durition = "JThreadGroup.Durition";

    public static final String NumOfThread = "JThreadGroup.NumOfThread";

    public static final String Delay = "JThreadGroup.Delay";

    public static final String Listeners = "JThreadGroup.Delay";

    public static final String Samples = "JThreadGroup.Samples";

    List<HttpSample> httpSamples = new ArrayList<>();

    //结果监听器集合
    List<IResultListener> resultListeners = new ArrayList<>();

    private long WAIT_TO_DIE = 60*1000;
    //所有线程
    Map<JRequestThread,Thread> allThreads = new ConcurrentHashMap<>();

    //是否运行
    boolean running = false;

    int numOfThread = 1;

    public void registerThread(JRequestThread requestThread,Thread thread){
        allThreads.put(requestThread,thread);
    }

    public void stop() {
        for (JRequestThread item : allThreads.keySet()) {
            item.stop();
        }
    }
    private boolean verifyThreadStopped(Thread thread) {
        boolean stopped = true;
        if (thread != null) {
            if (thread.isAlive()) {
                try {
                    thread.join(WAIT_TO_DIE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (thread.isAlive()) {
                    stopped = false;
                }
            }
        }
        return stopped;
    }

    private void waitThreadStopped(Thread thread) {
        if (thread != null) {
            while (thread.isAlive()) {
                try {
                    thread.join(WAIT_TO_DIE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * Wait for all Group Threads to stop
     */
    public void waitThreadsStopped() {
        for (JRequestThread t : allThreads.keySet()) {
            waitThreadStopped(allThreads.get(t));
        }
    }

    public boolean isThreadAllStoped(){
        boolean stoppedAll = true;
        for (JRequestThread item : allThreads.keySet()) {
            stoppedAll = stoppedAll && verifyThreadStopped(allThreads.get(item));
        }
        return stoppedAll;
    }

    private void pause(long ms){
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    public int numberOfActiveThreads() {
        return allThreads.size();
    }

    public JThreadGroup(){

    }
    public JThreadGroup(int numOfThread){
        this.numOfThread = numOfThread;
        this.setProperty(NumOfThread,numOfThread);
    }
    public void run(){

        running = true;

        float perThreadDelay = (float)(this.getPropertyInt(RAMP_UP) * 1000) / this.numOfThread;

        for(int i = 0;i<this.numOfThread;i++){
            JRequestThread jh = new JRequestThread();
            jh.setDelay((long)(i*perThreadDelay));
            jh.setThreadNum(i);
            jh.setThreadGroup(this);
            Thread thread = new Thread(jh);
            registerThread(jh,thread);
            thread.start();
        }
        waitThreadsStopped();
    }

    public void interupt(){

    }

    public void endEvent(){

    }

    public void setProperty(String name,String value){
        this.propMap.put(name,value);
    }
    public void setProperty(String name,int value){
        this.propMap.put(name,value);
    }
    public void setProperty(String name,long value){
        this.propMap.put(name,value);
    }
    public void setProperty(String name,Duration value){
        this.propMap.put(name,value);
    }
    public Integer getPropertyInt(String name){
        if(this.propMap.containsKey(name)){
            Object value = this.propMap.get(name);
            if(StrKit.isInteger(value)){
                return Integer.parseInt(value.toString());
            }
        }
        return null;
    }

    public Long getPropertyLong(String name){
        if(this.propMap.containsKey(name)){
            Object value = this.propMap.get(name);
            if(StrKit.isLong(value)){
                return Long.parseLong(value.toString());
            }
        }
        return null;
    }

    public List<HttpSample> getHttpSamples() {
        return httpSamples;
    }

    public void setHttpSamples(List<HttpSample> httpSamples) {
        this.httpSamples = httpSamples;
        this.propMap.put(Samples,httpSamples);
    }

    public List<IResultListener> getResultListeners() {
        return resultListeners;
    }

    public void setResultListeners(List<IResultListener> resultListeners) {
        this.resultListeners = resultListeners;
        this.propMap.put(Listeners,resultListeners);
    }
}
