package com.yt.http.request;

import com.yt.http.core.HttpSample;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;

abstract public class AbstractHttpService implements IHttpService{

    protected HttpClient httpClient;

    @Override
    public HttpClient createHttpClient() {
        httpClient = HttpClientBuilder.create().build();
        return this.httpClient;
    }

    protected RequestConfig requestConfig(HttpSample sample){
        RequestConfig.Builder builder = HttpClientConfig.getInstance().buildRequestConfig();
        builder.setConnectTimeout(sample.getConnect_timeout());
        builder.setConnectionRequestTimeout(sample.getRequest_timeout());
        builder.setSocketTimeout(sample.getSocket_timeout());
        builder.setRedirectsEnabled(sample.isRedirect_enable());

        return builder.build();
    }
    protected void buildRequestParams(HttpSample sample){

    }

    protected void buildRequestHeader(HttpSample sample){

    }

    @Override
    public abstract void request(HttpSample sample);
}
