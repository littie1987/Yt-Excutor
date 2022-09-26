package com.yt.http.core;

import com.yt.http.request.HttpClientConfig;

public class HttpRequetContext {

    HttpClientConfig httpClientConfig;

    public HttpRequetContext(){
        this.httpClientConfig = new HttpClientConfig();
    }

    public HttpClientConfig getHttpClientConfig() {
        return httpClientConfig;
    }

    public void setHttpClientConfig(HttpClientConfig httpClientConfig) {
        this.httpClientConfig = httpClientConfig;
    }
}
