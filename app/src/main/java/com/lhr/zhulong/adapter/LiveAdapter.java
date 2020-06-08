package com.lhr.zhulong.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.lhr.data.LiveBean;
import com.lhr.utils.newAdd.TimeUtil;
import com.lhr.zhulong.R;
import com.lhr.zhulong.ZLImageLoader;
import com.lhr.zhulong.design.RoundImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {

    private List<LiveBean> data = new ArrayList<>();
    private Context context;

    public LiveAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<LiveBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item_live, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LiveBean liveBean = data.get(position);
        holder.tvLiveName.setText(liveBean.getActivityName());

        ZLImageLoader.getInstance().displayImage(context, liveBean.getTeacher_photo(),holder.ivTeacherAvatar);
        int is_liveing = liveBean.getIs_liveing();
        if(is_liveing==0){
            holder.llLiving.setVisibility(View.GONE);
            holder.llLivePre.setVisibility(View.VISIBLE);
            holder.tvLiveTime.setText(TimeUtil.formatLiveTime(Long.valueOf(liveBean.getStartTime())));

        }else{
            holder.llLiving.setVisibility(View.VISIBLE);
            holder.llLivePre.setVisibility(View.GONE);
            ZLImageLoader.getInstance().displayImage(context,
                    ZLImageLoader.getUriFromResource(context, R.drawable.play), holder.ivLiving);
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_teacher_avatar)
        RoundImage ivTeacherAvatar;
        @BindView(R.id.tv_live_name)
        TextView tvLiveName;
        @BindView(R.id.iv_living)
        ImageView ivLiving;
        @BindView(R.id.ll_living)
        LinearLayout llLiving;
        @BindView(R.id.tv_live_time)
        TextView tvLiveTime;
        @BindView(R.id.ll_live_pre)
        LinearLayout llLivePre;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}


