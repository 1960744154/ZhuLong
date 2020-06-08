package com.lhr.zhulong.datafragment;

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


import com.lhr.data.InformationBean;
import com.lhr.data.SpecialtyChooseEntity;
import com.lhr.frame.ApiConfig;
import com.lhr.frame.constants.ConstantKey;
import com.lhr.frame.utils.ParamHashMap;
import com.lhr.utils.newAdd.SharedPrefrenceUtils;
import com.lhr.zhulong.R;
import com.lhr.zhulong.adapter.InformationAdapter;
import com.lhr.zhulong.base.BaseMvpFragment;
import com.lhr.zhulong.model.DataModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;



import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;



public class InformationFragment extends BaseMvpFragment<DataModel> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private InformationAdapter informationAdapter;
    private int type=1;
    private ParamHashMap ma_p;
    private List<InformationBean.ResultBean> list;
    private int fid;

    @Override
    public void setData() {
        ma_p = new ParamHashMap().add("page", 1).add("type", type).add("fid", fid);
        mPresenter.getData(ApiConfig.GET_INFORMATION_INFO,ma_p);

    }

    @Override
    public void setView() {
        if (SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.Constan)!=null){
            SpecialtyChooseEntity.DataBean bean = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.Constan);
            fid = bean.getFid();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
    //	//资料 资料小组
        //	https://bbs.zhulong.com/openapi/group/getGroupList
        //?page=1&type=1&fid=29&uid=15063681&time=1591368576&devices=oppoR11&
        //system=android,5.1.1&version=2.1.4&unique_id=355757265852349&client_id=205

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                type++;
                ma_p = new ParamHashMap().add("page", 1).add("type", type).add("fid", fid);
                mPresenter.getData(ApiConfig.GET_INFORMATION_INFO,ma_p);

                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                type=1;
                list.clear();
                ma_p = new ParamHashMap().add("page", 1).add("type", type).add("fid", fid);
                mPresenter.getData(ApiConfig.GET_INFORMATION_INFO,ma_p);
                refreshLayout.finishRefresh();
            }
        });
        list = new ArrayList<>();
        informationAdapter = new InformationAdapter(getActivity(), list);
        recyclerView.setAdapter(informationAdapter);

    }

    @Override
    public DataModel setModel() {
        return new DataModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.information_fragment;
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi){
            case  ApiConfig.GET_INFORMATION_INFO:
                InformationBean infor=( InformationBean) pD[0];
                list.addAll(infor.getResult());
                informationAdapter.notifyDataSetChanged();
                break;
        }

    }
}
