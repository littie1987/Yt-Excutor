package com.yt.http.core.summariser;

/**
 * @author libo
 * 统计结果类
 */
public class SummariserModel {

    /**
     * 请求个数
     */
    private long counter;
    /**
     * 最大时间
     */
    private long max;
    /**
     * 最短时间
     */
    private long min;
    /**
     * 错误数
     */
    private long errorCount;
    /**
     * 开始时间
     */
    private long startTime;
    /**
     * 结束时间
     */
    private long endTime;
}
