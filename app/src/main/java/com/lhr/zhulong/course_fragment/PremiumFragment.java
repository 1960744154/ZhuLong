package com.lhr.zhulong.course_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.lhr.data.SpecialtyChooseEntity;
import com.lhr.data.TrainningBean;
import com.lhr.frame.ApiConfig;
import com.lhr.frame.constants.ConstantKey;
import com.lhr.frame.utils.ParamHashMap;
import com.lhr.utils.newAdd.SharedPrefrenceUtils;
import com.lhr.zhulong.R;
import com.lhr.zhulong.adapter.PremiumAdapter;
import com.lhr.zhulong.base.BaseMvpFragment;
import com.lhr.zhulong.model.CouresModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：Leishen  秦宇
 * 创建于： 2020/6/5 23:25
 * 作者邮箱：1623060075@qq.com
 */

public class PremiumFragment extends BaseMvpFragment<CouresModel> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page=1;
    private String specialty_id;
    private ParamHashMap map_o;
    private List<TrainningBean.ResultBean.ListsBean> lists;
    private PremiumAdapter premiumAdapter;
    private int course_type;

    public PremiumFragment(int course_type) {
        this.course_type = course_type;
    }

    @Override
    public void setData() {

        map_o = new ParamHashMap().add("page", page).add("course_type", course_type)
                .add("limit", 15).add("specialty_id", specialty_id)
                .add("uid", 15063681).add("time", 1591366330);
        mPresenter.getData(ApiConfig.GET_TRAINING_INFO,map_o);

    }

    @Override
    public void setView() {
        if (SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.SUBJECT_SELECT)!=null){
            SpecialtyChooseEntity.DataBean bean = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.SUBJECT_SELECT);
            specialty_id = bean.getSpecialty_id();
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
               page++;

                map_o = new ParamHashMap().add("page", page).add("course_type", course_type)
                        .add("limit", 15).add("specialty_id", specialty_id)
                        .add("uid", 15063681).add("time", 1591366330);
                mPresenter.getData(ApiConfig.GET_TRAINING_INFO,map_o);
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                lists.clear();
                map_o = new ParamHashMap().add("page", page).add("course_type", course_type)
                        .add("limit", 15).add("specialty_id", specialty_id)
                        .add("uid", 15063681).add("time", 1591366330);
                mPresenter.getData(ApiConfig.GET_TRAINING_INFO,map_o);
                refreshLayout.finishRefresh();
            }
        });
        lists = new ArrayList<>();
        premiumAdapter = new PremiumAdapter(getActivity(),lists);
        recyclerView.setAdapter(premiumAdapter);

    }

    @Override
    public CouresModel setModel() {
        return new CouresModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.premium_fragment;
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi){
            case ApiConfig.GET_TRAINING_INFO:
                TrainningBean trainningBean= (TrainningBean) pD[0];
                    lists.addAll(trainningBean.getResult().getLists());
                    premiumAdapter.notifyDataSetChanged();
                break;
        }

    }
}
