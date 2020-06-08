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
import com.lhr.data.InformationBean;
import com.lhr.zhulong.R;


import java.util.List;


public class InformationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<InformationBean.ResultBean> list;

    public InformationAdapter(Context context, List<InformationBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.infor_item, null);
        return new ViewHolder1(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InformationBean.ResultBean bean = list.get(position);
        ViewHolder1 viewHolder1= (ViewHolder1) holder;
        viewHolder1.infor_title.setText(bean.getGroup_name());
        Glide.with(context).load(bean.getAvatar()).into(viewHolder1.infor_image);
        viewHolder1.infor_introduce.setText(bean.getIntroduce());
        viewHolder1.info_gz.setText(bean.getMember_num()+"关注");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder1  extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView infor_image;
        public TextView infor_title;
        public ImageView inf0_imag_tb;
        public TextView info_gz;
        public TextView infor_introduce;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.infor_image = (ImageView) rootView.findViewById(R.id.infor_image);
            this.infor_title = (TextView) rootView.findViewById(R.id.infor_title);
            this.inf0_imag_tb = (ImageView) rootView.findViewById(R.id.inf0_imag_tb);
            this.info_gz = (TextView) rootView.findViewById(R.id.info_gz);
            this.infor_introduce = (TextView) rootView.findViewById(R.id.infor_introduce);
        }

    }
}
