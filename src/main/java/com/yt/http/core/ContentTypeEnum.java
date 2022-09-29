package com.yt.http.core;

public enum ContentTypeEnum {

    JSON("application/json"),

    XML("application/xml"),

    URLENCODING("application/x-www-form-urlencoded"),

    PLAIN("application/plain"),

    FORMDATA("multipart/form-data");

    private String value;

    public String getValue(){return this.value;}

    ContentTypeEnum(String value){
        this.value = value;
    }

    public static ContentTypeEnum getContentTypeByValue(String value){
        for(ContentTypeEnum en :ContentTypeEnum.values()){
            if(en.getValue().equals(value))
                return en;
        }
        return null;
    }
}
