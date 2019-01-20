package com.example.yuekao3.mvp.pp;


import com.example.yuekao3.bean.MyData;
import com.example.yuekao3.mvp.model.ModelImpl;
import com.example.yuekao3.mvp.vieww.IView;
import com.example.yuekao3.mvp.vieww.MyCallback;

public class PerenterImpl implements Perenter {
    private IView iView;
    private ModelImpl model;

    public PerenterImpl(IView iView) {
        this.iView = iView;
        this.model = new ModelImpl();
    }

    @Override
    public void startRequest(String url) {
        model.getData(url, new MyCallback() {
            @Override
            public void setSuccess(MyData myData) {
                iView.setCheng(myData);
            }

            @Override
            public void setError(String error) {
                iView.setBai(error);
            }
        });
    }
}
