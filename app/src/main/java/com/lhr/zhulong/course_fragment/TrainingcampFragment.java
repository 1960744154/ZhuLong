package com.lhr.zhulong.course_fragment;

import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
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
import com.lhr.zhulong.adapter.TrainingAdapter;
import com.lhr.zhulong.base.BaseMvpFragment;
import com.lhr.zhulong.model.CouresModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：Leishen  秦宇
 * 创建于： 2020/6/5 23:22
 * 作者邮箱：1623060075@qq.com
 */

public class TrainingcampFragment extends BaseMvpFragment<CouresModel> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int page = 1;
    private String specialty_id;
    private ParamHashMap map;
    private List<TrainningBean.ResultBean.ListsBean> lists;
    private TrainingAdapter trainingAdapter;
    private int course_type;

    public TrainingcampFragment(int course_type) {
        this.course_type = course_type;
    }

    @Override
    public void setData() {
        map = new ParamHashMap().add("page", page).add("course_type", course_type)
                .add("limit", 15).add("specialty_id", specialty_id)
                .add("uid", 15063681).add("time", 1591366329);
        mPresenter.getData(ApiConfig.GET_TRAINING_INFO,map);

    }

    @Override
    public void setView() {
        if (SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.Constan) != null) {
            SpecialtyChooseEntity.DataBean bean = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.Constan);
            specialty_id = bean.getSpecialty_id();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
       refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
           @Override
           public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
               page++;
               map = new ParamHashMap().add("page", page).add("course_type", course_type)
                       .add("limit", 15).add("specialty_id", specialty_id)
                       .add("uid", 15063681).add("time", 1591366329);
               mPresenter.getData(ApiConfig.GET_TRAINING_INFO,map);
               refreshLayout.finishLoadMore();
           }

           @Override
           public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                lists.clear();
               map = new ParamHashMap().add("page", page).add("course_type", course_type)
                       .add("limit", 15).add("specialty_id", specialty_id)
                       .add("uid", 15063681).add("time", 1591366329);
               mPresenter.getData(ApiConfig.GET_TRAINING_INFO,map);
                refreshLayout.finishRefresh();
           }
       });
        lists = new ArrayList<>();
        trainingAdapter = new TrainingAdapter(getActivity(),lists);
        recyclerView.setAdapter(trainingAdapter);

    }

    @Override
    public CouresModel setModel() {
        return new CouresModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.traiing_frag;
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi){
            case ApiConfig.GET_TRAINING_INFO:
              TrainningBean trainningBean= (TrainningBean) pD[0];
               lists.addAll(trainningBean.getResult().getLists());
                trainingAdapter.notifyDataSetChanged();
                Log.i("1111111",  trainningBean.toString());
                break;
        }

    }
}
