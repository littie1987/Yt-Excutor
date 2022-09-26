package com.yt.http.core;

import org.apache.http.entity.mime.Header;

public class HttpEvent {

    private long startTime;

    private long endTime;

    private String responeCode;

    private String responseMessage;

    private byte[] responseData;

    private Header[] responseHeaders;
}
