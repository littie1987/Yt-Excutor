package com.yt.http.thread;

import com.yt.http.core.HttpSample;
import com.yt.http.listener.IResultListener;
import com.yt.http.request.HttpClientConfig;

import java.util.List;

public class JRequestThread implements Runnable{

    private HttpClientConfig httpClientConfig;

    private HttpSample sample;

    List<IResultListener> listeners;

    @Override
    public void run() {

    }
}
