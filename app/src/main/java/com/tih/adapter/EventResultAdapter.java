package com.tih.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tih.R;
import com.tih.bean.EventResult;

import java.util.List;

/**
 * Created by Billy on 2017/7/6.
 *
 *
 * Event 的 RecyclerView 的 Adapter
 */

public class EventResultAdapter extends RecyclerView.Adapter<EventResultAdapter.ViewHolder>{


    private List<EventResult> eventResultDataList;
    private OnItemClickListener mOnTtemClickListener;

    public EventResultAdapter(List<EventResult> data){
        //获取信息
        this.eventResultDataList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //获取View对象,绑定Layout
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.event_description,null);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //获取数据
        EventResult temp = eventResultDataList.get(position);

        //将数据加载到RecyclerView当中
        holder.date.setText(temp.getDate());
        holder.title.setText(temp.getTitle());


        //建立点击事件
        if(mOnTtemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnTtemClickListener.onClick(position);

                    Toast.makeText(holder.itemView.getContext(),
                            "OnClick",Toast.LENGTH_SHORT).show();
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnTtemClickListener.onLongClick(position);

                    Toast.makeText(holder.itemView.getContext(),
                            "OnLongClick",Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return eventResultDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;

        TextView date;
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            //绑定ID
            logo = (ImageView) itemView.findViewById(R.id.event_logo);

            date = (TextView) itemView.findViewById(R.id.event_date);
            title = (TextView) itemView.findViewById(R.id.event_title);

        }
    }


    //设置点击事件
    public interface OnItemClickListener{
        //点击
        void onClick(int position);
        //长按
        void onLongClick(int position);
    }

    public void setOnTtemClickListener(OnItemClickListener listener){
        this.mOnTtemClickListener = listener;
    }

}
