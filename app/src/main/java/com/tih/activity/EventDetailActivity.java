package com.tih.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import com.tih.R;
import com.tih.bean.Detail;
import com.tih.utility.HttpUtils;

import cz.msebera.android.httpclient.Header;

import static com.tih.utility.Constant.BASEDETAILURL;
import static com.tih.utility.Constant.DETAILID;
import static com.tih.utility.Constant.FIRST_IMAGE;
import static com.tih.utility.Constant.IMAGE_BASE_URL;
import static com.tih.utility.Constant.KEY;

/**
 * Created by DELL on 2017/7/14.
 */

public class EventDetailActivity extends AppCompatActivity{

    private TextView tv_title;

    private TextView tv_detail;

    private SimpleDraweeView iv_detail;

    private Detail detail;

    private Handler handler;

    private String myKey="b98b3f4d55a7b0af11e13475cc4aff08";

    private int e_id;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Fresco.initialize(this);
        init();
        loadDetail();
    }

    private void init(){

        tv_title = (TextView)findViewById(R.id.detail_title);
        tv_detail = (TextView)findViewById(R.id.detail_detail);
        iv_detail = (SimpleDraweeView)findViewById(R.id.detail_image);

        handler = new Handler();

    }

    private void loadDetail(){
        e_id = getIntent().getIntExtra("e_id",1);;
        HttpUtils.get(BASEDETAILURL+ KEY + myKey + DETAILID + e_id, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                parseDetailJson(responseString);

            }
        });
    }

    private void parseDetailJson(String responseString){

        Gson gson = new Gson();
        detail = gson.fromJson(responseString, Detail.class);

        handler.post(new Runnable() {
            @Override
            public void run() {
                tv_title.setText(detail.getResult().get(0).getTitle());
                tv_detail.setText(detail.getResult().get(0).getContent());

                Uri uri = Uri.parse(IMAGE_BASE_URL+e_id+FIRST_IMAGE);
                iv_detail.setImageURI(uri);
                Log.d("lzm", "run: "+uri);

            }
        });
    }
}
