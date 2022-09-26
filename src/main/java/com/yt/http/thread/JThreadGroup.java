package com.yt.http.thread;

import com.yt.http.core.HttpSample;
import com.yt.http.listener.IResultListener;
import com.yt.http.request.HttpClientConfig;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JThreadGroup {

    private String GroupName="Thread-Group-%s";

    //线程数量
    private int threads=1;

    private int delay = 0;
    //爬升率
    private int rampUp = 0;
    //-1表示无限循环
    private int loop = 1;
    //时间间隔
    private Duration duration = Duration.ofSeconds(0);

    private long WAIT_TO_DIE = 60*1000;

    //http采样集合
    List<HttpSample> httpSamples = new ArrayList<>();

    //结果监听器集合
    List<IResultListener> resultListeners = new ArrayList<>();

    //所有线程
    List<Thread> allThreads = new ArrayList<>();

    boolean running = false;

    public void registerThread(Thread thread){
        allThreads.add(thread);
    }

    public void stop() {
        for (Thread item : allThreads) {
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
                }
            }
        }
    }

    /**
     * Wait for all Group Threads to stop
     */
    public void waitThreadsStopped() {
        for (Thread t : allThreads) {
            waitThreadStopped(t);
        }
    }

    public boolean isThreadAllStoped(){
        boolean stoppedAll = true;
        for (Thread item : allThreads) {
            stoppedAll = stoppedAll && verifyThreadStopped(item);
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
    public void start(){

    }

    public void interupt(){

    }

    public void endEvent(){

    }
}
