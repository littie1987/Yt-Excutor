package com.yt.http.request;

import com.yt.http.core.HttpMethodEnum;
import com.yt.http.core.HttpSample;
import com.yt.http.core.RequestParam;
import com.yt.utils.StrKit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract public class AbstractHttpService implements IHttpService{

    protected HttpSample httpSample;

    protected String url;

    protected void checkProtocol(){
        if(this.url!=null){
            this.url = this.url.replaceFirst("^http://|^https://|HTTP://|HTTPS://","");
            this.url = httpSample.getProtocol()+"://"+url;
        }
    }

    /**
     * url中是否包含问号
     * @param url
     * @return
     */
    private boolean urlContainAskMark(String url){
        int len = this.url.length();
        int index = this.url.indexOf('?');
        if(index>0&&index<(len-1)){
            return true;
        }
        return false;
    }

    private RequestParam getRequestParamOfName(List<RequestParam> params,String name){
        if(params==null)
            return null;
        return params.stream()
                .filter(item->item.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    /**
     * 解析url中的参数，?号后面的参数
     * @return
     */
    protected List<RequestParam> getUrlParams(){
        List<RequestParam> params = null;
        if(urlContainAskMark(this.url)){
            String paramsString = this.url.substring(this.url.indexOf('?')+1);
            for(String pairs : paramsString.split("&")){
                String[] paramMapArr = pairs.split("=");
                if(paramMapArr.length==2 && StrKit.isVariableNameRight(paramMapArr[0])){
                    if(params==null)
                        params = new ArrayList<>();
                    RequestParam mt = getRequestParamOfName(params,paramMapArr[0]);
                    if(mt==null)
                        params.add(new RequestParam(paramMapArr[0],paramMapArr[1]));
                    else
                        mt.setValue(paramMapArr[1]);
                }
            }
        }
        return params;
    }

    /**
     * Get请求，设置url参数
     */
    public void setGetUrlParams(){
        if(HttpMethodEnum.GET==httpSample.getMethod()){
            List<RequestParam> urlParams = getUrlParams();
            if(urlParams==null)
                urlParams = new ArrayList<>();
            List<RequestParam> params = httpSample.getParams();
            if(params!=null){
                params = params.stream()
                        .filter(item->StrKit.isVariableNameRight(item.getName()))
                        .collect(Collectors.toList());
                for(RequestParam param : params){
                    RequestParam mt = getRequestParamOfName(urlParams,param.getName());
                    if(mt==null)
                        urlParams.add(param);
                    else
                        mt.setValue(param.getValue());
                }
            }
            if(urlContainAskMark(this.url)&&!urlParams.isEmpty()){
                this.url = this.url.substring(0,this.url.indexOf('?'));
                StringBuffer _url = new StringBuffer(this.url.substring(0,this.url.indexOf('?')));
                _url.append('?');
                urlParams.forEach(item->{
                    _url.append(item.getName());
                    _url.append('=');
                    _url.append(item.getValue());
                    _url.append('&');
                });
                if(_url.charAt(_url.length()-1)=='&')
                    _url.deleteCharAt(_url.length()-1);
                this.url+=_url.toString();
            }
        }
    }
}
