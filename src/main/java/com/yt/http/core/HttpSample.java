package com.yt.http.core;

import com.yt.http.request.HttpClientConfig;
import com.yt.http.request.HttpMethods;
import org.apache.http.entity.mime.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpSample {

    //延时
    private int delay = 0;

    //获取连接超时时间
    private int connect_timeout = HttpClientConfig.getInstance().getConnect_timeout();

    //连接超时时间
    private int request_timeout = HttpClientConfig.getInstance().getRequest_connect_timeout();

    //数据传输超时时间
    private int socket_timeout = HttpClientConfig.getInstance().getSocket_timeout();

    //结果保存目录
    private String result_save_dir=HttpClientConfig.getInstance().getResponse_save_dir();

    private String url;

    private HttpMethods method;

    //参数
    private List<RequestParam> params;

    private List<RequestHeader> headers;

    /**
     * 添加请求参数
     * @param name
     * @param value
     * @return
     */
    public HttpSample addParam(String name,Object value){
        if(name!=null&&value!=null){
            if(params==null){
                params = new ArrayList<>();
                params.add(new RequestParam(name,value));
            }else{
                RequestParam mt = null;
                for(RequestParam param : params){
                    if(name.equalsIgnoreCase(param.getName())){
                        mt = param;
                        break;
                    }
                }
                if(mt!=null){
                    mt.setValue(value);
                }else{
                    params.add(new RequestParam(name,value));
                }
            }
        }
        return this;
    }

    /**
     * 添加头信息
     * @param name
     * @param value
     * @return
     */
    public HttpSample addHeader(String name,Object value){
        if(name!=null&&value!=null){
            if(headers==null){
                headers = new ArrayList<>();
                headers.add(new RequestHeader(name,value));
            }else{
                RequestHeader mt = null;
                for(RequestHeader header : headers){
                    if(name.equalsIgnoreCase(header.getName())){
                        mt = header;
                        break;
                    }
                }
                if(mt!=null){
                    mt.setValue(value);
                }else{
                    headers.add(new RequestHeader(name,value));
                }
            }
        }
        return this;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getConnect_timeout() {
        return connect_timeout;
    }

    public void setConnect_timeout(int connect_timeout) {
        this.connect_timeout = connect_timeout;
    }

    public int getRequest_timeout() {
        return request_timeout;
    }

    public void setRequest_timeout(int request_timeout) {
        this.request_timeout = request_timeout;
    }

    public int getSocket_timeout() {
        return socket_timeout;
    }

    public void setSocket_timeout(int socket_timeout) {
        this.socket_timeout = socket_timeout;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethods getMethod() {
        return method;
    }

    public void setMethod(HttpMethods method) {
        this.method = method;
    }
}