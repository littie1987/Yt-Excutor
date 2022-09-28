package com.yt.http.core;

import org.apache.http.entity.mime.Header;

public class HttpEvent {

    private long startTime;

    private long endTime;

    HttpSampleResult result;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public HttpSampleResult getResult() {
        return result;
    }

    public void setResult(HttpSampleResult result) {
        this.result = result;
    }
}
