package com.lhr.zhulong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lhr.data.EssBean;
import com.lhr.utils.newAdd.TimeUtil;
import com.lhr.zhulong.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EssAdapter extends RecyclerView.Adapter<EssAdapter.ViewHolder> {

    private Context context;
    private List<EssBean> data = new ArrayList<>();

    public EssAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<EssBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_list_mianpage_one, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EssBean essBean = data.get(position);
        Glide.with(context).load(essBean.getPic()).into(holder.ivPhoto);
        holder.listOneTitle.setText(essBean.getTitle());
        holder.tvBrowseNum.setText(essBean.getView_num()+"人浏览");
        holder.tvCommentNum.setText(essBean.getReply_num()+"人跟帖");
        holder.tvAuthorAndTime.setText(TimeUtil.formatLiveTime(Long.valueOf(essBean.getCreate_time())));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_one_title)
        TextView listOneTitle;
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.cv_photo)
        CardView cvPhoto;
        @BindView(R.id.tv_browse_num)
        TextView tvBrowseNum;
        @BindView(R.id.tv_comment_num)
        TextView tvCommentNum;
        @BindView(R.id.tv_author_and_time)
        TextView tvAuthorAndTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
