package com.yt.http.request;

import com.yt.http.core.HttpMethodEnum;
import com.yt.http.core.HttpSample;
import com.yt.http.core.RequestParam;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientServiceImpl extends AbstractHttpService{

    private CloseableHttpClient httpClient;

    public HttpRequestBase createHttpBase(HttpMethodEnum method){
        if(method==HttpMethodEnum.GET){
            return new HttpGet(url);
        }else if(method==HttpMethodEnum.POST){
            return new HttpPost(url);
        }else if(method==HttpMethodEnum.PATCH){
            return new HttpPatch(url);
        }else if(method==HttpMethodEnum.DELETE){
            return new HttpDelete(url);
        }else if(method==HttpMethodEnum.PUT){
            return new HttpPut(url);
        }
        return null;
    }
    public HttpClient createHttpClient() {
        httpClient = HttpClientBuilder.create().build();
        return this.httpClient;
    }

    private RequestConfig requestConfig(HttpSample sample){
        RequestConfig.Builder builder = HttpClientConfig.getInstance().buildRequestConfig();
        builder.setConnectTimeout(sample.getConnect_timeout());
        builder.setConnectionRequestTimeout(sample.getRequest_timeout());
        builder.setSocketTimeout(sample.getSocket_timeout());
        builder.setRedirectsEnabled(sample.isRedirect_enable());
        return builder.build();
    }

    private void buildRequestParams(HttpRequestBase base){
        if(HttpMethodEnum.GET==httpSample.getMethod()){
            setGetUrlParams();
            return;
        }
        List<RequestParam> params = httpSample.getParams();
        if(params!=null){
            List<NameValuePair> pairs = new ArrayList<>();
            for(RequestParam param : params){
                pairs.add(param.convert2Pair());
            }
            if(!pairs.isEmpty())

        }
    }

    protected void buildRequestHeader(HttpSample sample){

    }

    @Override
    public void request(HttpSample sample){
        this.httpSample = sample;
        this.url = sample.getUrl();
        try {
            // 创建httpclient实例
            createHttpClient();

            HttpRequestBase base = createHttpBase(sample.getMethod());
            if(base==null){
                throw new IllegalArgumentException("the http method["+sample.getMethod()+"] is not avaliable.");
            }
            // set config params
            base.setConfig(requestConfig(sample));

            //设置访问参数
            buildRequestParams(sample);




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    this.httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    };
}
