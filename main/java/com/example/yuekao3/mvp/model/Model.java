package com.example.yuekao3.mvp.model;


import com.example.yuekao3.mvp.vieww.MyCallback;

public interface Model {
    void getData(String url, MyCallback callback);
}
