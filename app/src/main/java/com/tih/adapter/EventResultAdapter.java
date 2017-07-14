package com.tih.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tih.R;
import com.tih.activity.EventDetailActivity;
import com.tih.bean.EventResult;

import java.util.List;

import static com.tih.utility.Constant.FIRST_IMAGE;
import static com.tih.utility.Constant.IMAGE_BASE_URL;

/**
 * Created by Billy on 2017/7/6.
 *
 *
 * Event 的 RecyclerView 的 Adapter
 */

public class EventResultAdapter extends RecyclerView.Adapter<EventResultAdapter.ViewHolder>{


    private List<EventResult> eventResultDataList;
    private Context context;
    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout container;
        SimpleDraweeView logo;

        TextView date;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            //绑定ID
            container=(LinearLayout)itemView.findViewById(R.id.item_container);
            logo = (SimpleDraweeView) itemView.findViewById(R.id.event_logo);

            date = (TextView) itemView.findViewById(R.id.event_date);
            title = (TextView) itemView.findViewById(R.id.event_title);

        }
    }

    public EventResultAdapter(List<EventResult> data){
        //获取信息
        this.eventResultDataList = data;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context==null){
            context = parent.getContext();
        }
        //获取View对象,绑定Layout
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.event_description,null);
        final ViewHolder holder = new ViewHolder(itemView);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("e_id",eventResultDataList.get(position).getE_id());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,int position) {
        //获取数据
        EventResult temp = eventResultDataList.get(position);
        Log.d("lyw", "onBindViewHolder: "+temp);

        //加载图片
        //holder.logo.setImageResource(R.mipmap.ic_launcher);
        Uri uri = Uri.parse(IMAGE_BASE_URL+temp.getE_id()+FIRST_IMAGE);
        Log.d("lyw", "onBindViewHolder: "+uri);
        holder.logo.setImageURI(uri);
        //将数据加载到RecyclerView当中
        holder.date.setText(temp.getDate());
        holder.title.setText(temp.getTitle());


    }

    @Override
    public int getItemCount() {
        return eventResultDataList.size();
    }


}
