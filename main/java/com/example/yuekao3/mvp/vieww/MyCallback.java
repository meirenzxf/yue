package com.example.yuekao3.mvp.vieww;


import com.example.yuekao3.bean.MyData;

public interface MyCallback {
    void setSuccess(MyData myData);
    void setError(String error);
}
