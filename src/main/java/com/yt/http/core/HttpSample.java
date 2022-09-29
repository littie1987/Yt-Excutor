package com.yt.http.core;

import com.yt.http.request.HttpClientConfig;
import com.yt.http.request.HttpMethods;
import com.yt.utils.StrKit;
import org.apache.commons.net.util.Charsets;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
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

    //是否允许重定向
    private boolean redirect_enable = HttpClientConfig.getInstance().isRedirect_enable();

    private String url;

    private HttpMethodEnum method;

    //协议(http https)
    private String protocol="http";

    //参数
    private List<RequestParam> params;

    private List<RequestHeader> headers;

    //编码
    private Charset requestEncoding = Charset.defaultCharset();

    private ContentTypeEnum contentType;


    public HttpSample() {

    }

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
        this.url = StrKit.trim(url);
    }

    public HttpMethodEnum getMethod() {
        return method;
    }

    public void setMethod(HttpMethodEnum method) {
        this.method = method;
    }

    public boolean isRedirect_enable() {
        return redirect_enable;
    }

    public void setRedirect_enable(boolean redirect_enable) {
        this.redirect_enable = redirect_enable;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public List<RequestParam> getParams() {
        return params;
    }

    public void setParams(List<RequestParam> params) {
        this.params = params;
    }

    public List<RequestHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<RequestHeader> headers) {
        this.headers = headers;
    }
}
