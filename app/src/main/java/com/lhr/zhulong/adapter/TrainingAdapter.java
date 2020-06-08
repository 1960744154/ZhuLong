package com.lhr.zhulong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lhr.data.TrainningBean;
import com.lhr.zhulong.R;


import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TrainningBean.ResultBean.ListsBean> lists;

    public TrainingAdapter(Context context, List<TrainningBean.ResultBean.ListsBean> lists) {
        this.context = context;
        this.lists = lists;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.premium_item, null);

        return new ViewHolder1(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TrainningBean.ResultBean.ListsBean bean = lists.get(position);
        com.lhr.zhulong.adapter.PremiumAdapter.ViewHolder1 viewHolder1 = (com.lhr.zhulong.adapter.PremiumAdapter.ViewHolder1) holder;
        viewHolder1.tv_lesson_name.setText(bean.getLesson_name());
        Glide.with(context).load(bean.getThumb()).into(viewHolder1.img_thumb);
        viewHolder1.tv_price.setText("￥"+bean.getPrice());
        viewHolder1.studentnum.setText(bean.getStudentnum()+"人学习");
        viewHolder1.tv_rate.setText("好评率"+bean.getRate()+"%");
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


    public static
    class ViewHolder1  extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView img_thumb;
        public TextView tv_lesson_name;
        public ImageView img_prople;
        public TextView studentnum;
        public ImageView img_like;
        public TextView tv_rate;
        public TextView tv_price;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_thumb = (ImageView) rootView.findViewById(R.id.img_thumb);
            this.tv_lesson_name = (TextView) rootView.findViewById(R.id.tv_lesson_name);
            this.img_prople = (ImageView) rootView.findViewById(R.id.img_prople);
            this.studentnum = (TextView) rootView.findViewById(R.id.studentnum);
            this.img_like = (ImageView) rootView.findViewById(R.id.img_like);
            this.tv_rate = (TextView) rootView.findViewById(R.id.tv_rate);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
        }

    }
}
