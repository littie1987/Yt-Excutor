package com.yt.http.core;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class RequestParam {

    private String name;

    private Object value;

    public RequestParam(String name,Object value){
        this.name = name;
        this.value = value;
    }

    public NameValuePair convert2Pair(){
        return new BasicNameValuePair(getName(), String.valueOf(getValue()));
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RequestParam{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
