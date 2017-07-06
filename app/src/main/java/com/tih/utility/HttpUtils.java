package com.tih.utility;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.ResponseHandlerInterface;

/**
 * Created by DELL on 2017/7/6.
 */

public class HttpUtils {

    private static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    public static void get(String url, ResponseHandlerInterface responseHandlerInterface){
        asyncHttpClient.get(url, responseHandlerInterface);
    }

}
