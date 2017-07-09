package com.tih.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import com.tih.adapter.EventResultAdapter;
import com.tih.R;
import com.tih.bean.Event;
import com.tih.bean.EventResult;
import com.tih.utility.HttpUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.tih.utility.Constant.BASEEVENTURL;
import static com.tih.utility.Constant.EVENTDATE;
import static com.tih.utility.Constant.KEY;

public class MainActivity extends AppCompatActivity {

    private List<EventResult> mEventResultList;
    private RecyclerView rv_EventList;

    private String myKey="b98b3f4d55a7b0af11e13475cc4aff08";

    private String date;//用于url中

    private int mMonth;

    private int mDay;

    private Event events;

    private Handler handler = new Handler();

    private EventResultAdapter adapter ;

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
        Log.d("lyw", "onCreate: "+date);

        initWidget();

        showEventList();
        Log.d("lyw", "onSuccess: "+BASEEVENTURL + KEY + myKey + EVENTDATE + date);
    }

    private void initWidget(){
        rv_EventList = (RecyclerView) findViewById(R.id.event_list);
    }

    private void showEventList(){

        //initData();

        loadEvent();


        //加入布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_EventList.setLayoutManager(layoutManager);

        //加入设配器
        //rv_EventList.setAdapter(adapter);

        //加入动画
        rv_EventList.setItemAnimator(new DefaultItemAnimator());

    }

    //建立假的数据
    private void initData(){
        loadEvent();
        mEventResultList = new ArrayList<>();
        for(int i = 0;i < events.getResult().size();i++){


            mEventResultList.add(events.getResult().get(i));
        }

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
        events = gson.fromJson(responseString,Event.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                List<EventResult> temp = events.getResult();
                adapter=new EventResultAdapter(temp);
                Log.d("lyw", "run: " + temp);
                rv_EventList.setAdapter(adapter);
            }
        });
    }
}
