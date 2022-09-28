package com.yt.http.thread;

import com.yt.http.core.HttpSample;
import com.yt.http.listener.IResultListener;
import com.yt.http.request.HttpClientConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JRequestThread implements Runnable{

    private HttpClientConfig httpClientConfig = HttpClientConfig.getInstance();

    List<HttpSample> httpSamples;

    //结果监听器集合
    List<IResultListener> resultListeners;

    private int threadNum;

    private JThreadGroup threadGroup;

    private boolean running = false;

    //延迟运行时间
    private long delay = 0;

    /**
     * 运行http测试
     */
    @Override
    public void run() {
        running=true;

    }

    public void stop(){
        running = false;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public JThreadGroup getThreadGroup() {
        return threadGroup;
    }

    public void setThreadGroup(JThreadGroup threadGroup) {
        this.threadGroup = threadGroup;
    }

    public void delay(){
        if(delay<=0)
            return;
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
        }
    }

    public List<HttpSample> getHttpSamples() {
        return httpSamples;
    }

    public void setHttpSamples(List<HttpSample> httpSamples) {
        this.httpSamples = httpSamples;
    }

    public List<IResultListener> getResultListeners() {
        return resultListeners;
    }

    public void setResultListeners(List<IResultListener> resultListeners) {
        this.resultListeners = resultListeners;
    }
}
