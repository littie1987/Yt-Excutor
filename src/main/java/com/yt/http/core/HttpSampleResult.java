package com.yt.http.core;

public class HttpSampleResult {
    private long startTime;
    private long endTime;
    private String responeCode;
    private String responseMessage="";
    private byte[] responseData = new byte[0];
    private String responseHeaders = ""; // Never return null
    private String contentType = ""; // e.g. text/html; charset=utf-8

    boolean success = true;

    public String getResponeCode() {
        return responeCode;
    }

    public void setResponeCode(String responeCode) {
        this.responeCode = responeCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public byte[] getResponseData() {
        return responseData;
    }

    public void setResponseData(byte[] responseData) {
        this.responseData = responseData;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(String responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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
}
