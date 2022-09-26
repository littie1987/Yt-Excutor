package com.yt.http.request;

import com.yt.http.core.HttpCommons;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.client.config.RequestConfig;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class HttpClientConfig {

    private int connect_timeout = 2000;

    private int request_connect_timeout = 6000;

    private int socket_timeout = 20000;

    private String localaddress=null;

    private boolean redirect_enable = false;

    private String response_encoding = "UTF-8";

    private String response_save_dir="/tmp/yt/logs";

    private String prefix = "httpclient";

    private Properties config;

    public static HttpClientConfig instance;
    PropertyDescriptor[] propertyDescriptors;

    private static String LOCK="lock";
    public HttpClientConfig(){
        this.init();
    }
    private HttpClientConfig init(){
        this.loadProperties();
        this.readConfig();
        return this;
    }

    public static HttpClientConfig getInstance(){
        if(instance==null){
            synchronized(LOCK){
                if(instance==null)
                    instance = new HttpClientConfig();
            }
        }
        return instance;
    }

    public RequestConfig buildRequestConfig(){
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(this.connect_timeout)
                .setConnectionRequestTimeout(this.request_connect_timeout)
                .setRedirectsEnabled(this.redirect_enable)
                .setSocketTimeout(this.socket_timeout)
//                .setLocalAddress(this.localaddress==null?null:InetAddressgetByName(this.localaddress))
                .build();
        return requestConfig;
    }

    public void loadProperties(String propertiesFileName){
        try {
            Properties pro = new Properties();
            pro.load(HttpClientConfig.class.getResourceAsStream(propertiesFileName));
            config = pro;
        } catch (IOException e) {
            throw new RuntimeException("could not find httpconfig properties file",e);
        }
    }
    public void loadProperties(){
        loadProperties(HttpCommons.httpConfigFileName);
    }

    private synchronized Method getWriteMethod(String fieldName){
        if(propertyDescriptors==null)
            propertyDescriptors = PropertyUtils.getPropertyDescriptors(this.getClass());
        for(PropertyDescriptor pdes : propertyDescriptors){
            if(pdes.getName().contentEquals(fieldName)){
                return pdes.getWriteMethod();
            }
        }
        return null;
    }
    /**
     * 读取http配置文件
     */
    public void readConfig(){
        if(config==null)
            return;
        List<String> httpConfigNames = new ArrayList<>();
        Enumeration proNames = config.propertyNames();
        while (proNames.hasMoreElements()){
            String pn = (String)proNames.nextElement();
            if(pn.startsWith(prefix))
                httpConfigNames.add(pn);
        }
        String configName = "";
        try {
            for(String cn : httpConfigNames){
                configName = cn;
                String pure_name = cn.replace(prefix+".","").replaceAll(".","_");
                Method wMethod = getWriteMethod(pure_name);
                Object value = config.get(cn);
                if(wMethod!=null)
                    wMethod.invoke(this,value);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("set http config["+configName+"] failure",e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("set http config["+configName+"] failure",e);
        }
    }

    public int getConnect_timeout() {
        return connect_timeout;
    }

    public void setConnect_timeout(int connect_timeout) {
        this.connect_timeout = connect_timeout;
    }

    public int getRequest_connect_timeout() {
        return request_connect_timeout;
    }

    public void setRequest_connect_timeout(int request_connect_timeout) {
        this.request_connect_timeout = request_connect_timeout;
    }

    public int getSocket_timeout() {
        return socket_timeout;
    }

    public void setSocket_timeout(int socket_timeout) {
        this.socket_timeout = socket_timeout;
    }

    public String getLocaladdress() {
        return localaddress;
    }

    public void setLocaladdress(String localaddress) {
        this.localaddress = localaddress;
    }

    public boolean isRedirect_enable() {
        return redirect_enable;
    }

    public void setRedirect_enable(boolean redirect_enable) {
        this.redirect_enable = redirect_enable;
    }

    public void setResponse_encoding(String response_encoding) {
        this.response_encoding = response_encoding;
    }

    public String getResponse_save_dir() {
        return response_save_dir;
    }

    public void setResponse_save_dir(String response_save_dir) {
        this.response_save_dir = response_save_dir;
    }

    public String getResponse_encoding() {
        return response_encoding;
    }
}
