package com.example.yuekao3.mvp.model;

import android.os.Handler;
import android.os.Message;

import com.example.yuekao3.bean.MyData;
import com.example.yuekao3.mvp.vieww.MyCallback;
import com.example.yuekao3.utils.OkHttps;
import com.google.gson.Gson;

import java.io.IOException;


public class ModelImpl implements Model {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String jsonstr = (String) msg.obj;
                    Gson gson = new Gson();
                    MyData myData = gson.fromJson(jsonstr, MyData.class);
                    callback.setSuccess(myData);
                    break;
            }
        }
    };

    private MyCallback callback;

    @Override
    public void getData(final String url, MyCallback callback) {
        this.callback = callback;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String ok= OkHttps.getInstance().get(url);
                    handler.sendMessage(handler.obtainMessage(0,ok));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
