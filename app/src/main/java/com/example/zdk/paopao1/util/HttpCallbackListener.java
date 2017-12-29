package com.example.zdk.paopao1.util;

/**
 * Created by Administrator on 2017/12/1.
 */

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
