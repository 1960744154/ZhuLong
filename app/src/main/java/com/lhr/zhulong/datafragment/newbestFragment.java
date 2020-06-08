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


import com.lhr.data.NewbestBean;
import com.lhr.data.SpecialtyChooseEntity;
import com.lhr.frame.ApiConfig;
import com.lhr.frame.ICommonModel;
import com.lhr.frame.constants.ConstantKey;
import com.lhr.frame.utils.ParamHashMap;
import com.lhr.utils.newAdd.SharedPrefrenceUtils;
import com.lhr.zhulong.R;
import com.lhr.zhulong.adapter.NewBestAdapter;
import com.lhr.zhulong.base.BaseMvpFragment;
import com.lhr.zhulong.model.DataModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;



import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class newbestFragment extends BaseMvpFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private NewBestAdapter newBestAdapter;
    private List<NewbestBean.ResultBean> list;
    private int page=1;
    private int fid;
    private ParamHashMap map_add;

    @Override
    public void setData() {
        map_add = new ParamHashMap().add("page", page).add("fid", fid);
        mPresenter.getData(ApiConfig.GET_NEWBEST_INFO,map_add);

    }

    @Override
    public void setView() {
        if ( SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.Constan)!=null){
            SpecialtyChooseEntity.DataBean bean=  SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.Constan);
            fid = bean.getFid();
        }
        SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.Constan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        // https://bbs.zhulong.com/openapi/group/getThreadEssence
        // ?page=1&fid=29&uid=15063681&time=1591368576
        // &devices=oppoR11&system=android,5.1.1&version=2.1.4
        // &unique_id=355757265852349&client_id=205
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                map_add = new ParamHashMap().add("page", page).add("fid", fid);
                mPresenter.getData(ApiConfig.GET_NEWBEST_INFO,map_add);

                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                list.clear();
                map_add = new ParamHashMap().add("page", page).add("fid", fid);
                mPresenter.getData(ApiConfig.GET_NEWBEST_INFO,map_add);
                refreshLayout.finishRefresh();
            }
        });

        list = new ArrayList<>();
        newBestAdapter = new NewBestAdapter(getActivity(), list);
        recyclerView.setAdapter(newBestAdapter);
    }

    @Override
    public ICommonModel setModel() {
        return new DataModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.newbest_fragment;
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi){
            case ApiConfig.GET_NEWBEST_INFO:
                NewbestBean newbestBean=(NewbestBean)pD[0];
                list.addAll(newbestBean.getResult());
                newBestAdapter.notifyDataSetChanged();
                break;
        }

    }
}
