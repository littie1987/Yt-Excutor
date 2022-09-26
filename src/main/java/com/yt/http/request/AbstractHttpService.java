package com.yt.http.request;

import com.yt.http.core.HttpSample;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class AbstractHttpService implements IHttpService{

    protected HttpClient httpClient;

    @Override
    public HttpClient createHttpClient() {
        httpClient = HttpClientBuilder.create().build();
        return this.httpClient;
    }

    @Override
    public void request(HttpSample sample) {

    }
}
