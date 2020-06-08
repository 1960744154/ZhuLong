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
import com.lhr.data.NewbestBean;
import com.lhr.zhulong.R;


import java.util.List;



public class NewBestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<NewbestBean.ResultBean> list;

    public NewBestAdapter(Context context, List<NewbestBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.newbest_item, null);

        return new ViewHolder1(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewbestBean.ResultBean bean = list.get(position);
            ViewHolder1 viewHolder1= (ViewHolder1) holder;
            viewHolder1.tv_title.setText(bean.getTitle());
        Glide.with(context).load(bean.getPic()).into(viewHolder1.img_pic);
        viewHolder1.tv_view_num.setText(bean.getView_num()+"人");
        viewHolder1.tv_reply_num.setText(bean.getReply_num()+"跟帖");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv_title;
        public TextView tv_view_num;
        public TextView tv_reply_num;
        public ImageView img_pic;
        public TextView tv_gid;

        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_view_num = (TextView) rootView.findViewById(R.id.tv_view_num);
            this.tv_reply_num = (TextView) rootView.findViewById(R.id.tv_reply_num);
            this.img_pic = (ImageView) rootView.findViewById(R.id.img_pic);
            this.tv_gid = (TextView) rootView.findViewById(R.id.tv_gid);
        }

    }
}
