package com.yt.http.thread;

import com.yt.http.core.HttpSample;
import com.yt.http.listener.IResultListener;
import com.yt.http.request.HttpClientConfig;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

    //http采样集合
    List<HttpSample> httpSamples = new ArrayList<>();

    //结果监听器集合
    List<IResultListener> resultListeners = new ArrayList<>();

    public JThreadGroup(){

    }
    public void start(){

    }

    public void interupt(){

    }

    public void endEvent(){

    }
}
