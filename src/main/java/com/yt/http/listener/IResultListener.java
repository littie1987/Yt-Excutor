package com.yt.http.listener;

import com.yt.http.core.HttpEvent;

public interface IResultListener {

    public void startEvent(HttpEvent result);

    public void endEvent(HttpEvent result);
}
