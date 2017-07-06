package com.tih.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import com.tih.R;
import com.tih.bean.Event;
import com.tih.utility.HttpUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

import static com.tih.utility.Constant.BASEEVENTURL;
import static com.tih.utility.Constant.EVENTDATE;
import static com.tih.utility.Constant.KEY;

public class MainActivity extends AppCompatActivity {

    private String myKey="b98b3f4d55a7b0af11e13475cc4aff08";

    private String date;//用于url中

    private int mMonth;

    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date mDate=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm E");
        String str_time = sdf.format(mDate);

        mMonth=(str_time.charAt(5)-48)*10+(str_time.charAt(6)-48);
        mDay=(str_time.charAt(8)-48)*10+(str_time.charAt(9)-48);

        date = mMonth+"/"+mDay;
    }

    private void loadEvent(){
        HttpUtils.get(BASEEVENTURL + KEY + myKey + EVENTDATE + date, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                parseEventJson(responseString);
            }
        });
    }

    private void parseEventJson(String responseString){
        Gson gson = new Gson();
        Event events = gson.fromJson(responseString,Event.class);
    }
}
