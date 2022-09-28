package com.yt.http.core;

import com.yt.http.request.HttpClientConfig;
import com.yt.http.thread.JRequestThread;
import com.yt.http.thread.JThreadGroup;

public class HttpRequetContext {

    HttpClientConfig httpClientConfig;

    // 取样请求数据
    HttpSample sample;

    // 取样结果数据
    HttpSampleResult sampleResult;

    //当前线程对象
    JRequestThread currrentThread;

    //当前线程组
    JThreadGroup threadGroup;

    public HttpRequetContext(){
        this.httpClientConfig = new HttpClientConfig();
    }

    public HttpClientConfig getHttpClientConfig() {
        return httpClientConfig;
    }

    public void setHttpClientConfig(HttpClientConfig httpClientConfig) {
        this.httpClientConfig = httpClientConfig;
    }

    public HttpSample getSample() {
        return sample;
    }

    public void setSample(HttpSample sample) {
        this.sample = sample;
    }

    public HttpSampleResult getSampleResult() {
        return sampleResult;
    }

    public void setSampleResult(HttpSampleResult sampleResult) {
        this.sampleResult = sampleResult;
    }

    public JRequestThread getCurrrentThread() {
        return currrentThread;
    }

    public void setCurrrentThread(JRequestThread currrentThread) {
        this.currrentThread = currrentThread;
    }

    public JThreadGroup getThreadGroup() {
        return threadGroup;
    }

    public void setThreadGroup(JThreadGroup threadGroup) {
        this.threadGroup = threadGroup;
    }
}
