package com.yt.http.request;

import com.yt.http.core.HttpMethodEnum;
import com.yt.http.core.HttpSample;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.mime.Header;

import java.util.Map;

public interface IHttpService {

    public void request(HttpSample sample);
}
