package com.yt.http.core;

public class HttpRequestContextManager {

    public static ThreadLocal<HttpRequetContext> threadLocalUser = new ThreadLocal<>(

    );
}
