package com.yt.http.core;

import com.yt.http.listener.IResultListener;
import com.yt.http.thread.JThreadGroup;
import org.apache.poi.ss.formula.functions.T;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ExcuteTask {
    //线程数量
    private int numOfThread=1;

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

    List<JThreadGroup> groups = new ArrayList<>();

    public void run(){
        if(numOfThread<0)
            throw new IllegalArgumentException("numOfThread property must >0 ");
        if(rampUp<0)
            throw new IllegalArgumentException("rampup property must >=0");
        if(delay<0)
            delay = 0;
        if(loop<=-1)
            loop = loop = Integer.MAX_VALUE;

        JThreadGroup jThreadGroup = new JThreadGroup(numOfThread);
        jThreadGroup.setProperty(JThreadGroup.LOOP,loop);
        jThreadGroup.setProperty(JThreadGroup.Durition,duration);
        jThreadGroup.setProperty(JThreadGroup.RAMP_UP,rampUp);
        jThreadGroup.setProperty(JThreadGroup.Durition,duration);
        jThreadGroup.setProperty(JThreadGroup.Delay,delay);
        jThreadGroup.setResultListeners(resultListeners);
        jThreadGroup.setHttpSamples(httpSamples);
        Thread th = new Thread(jThreadGroup);
        th.start();
    }
}
