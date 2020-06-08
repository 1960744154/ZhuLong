package com.lhr.zhulong.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.lhr.frame.CommonPresenter;
import com.lhr.frame.ICommonModel;
import com.lhr.frame.ICommonView;

import butterknife.ButterKnife;
import butterknife.Unbinder;



public abstract  class BaseMvpFragment<M extends ICommonModel>extends com.lhr.zhulong.base.BaseFragment implements ICommonView {
    private M mModel;
    private Unbinder mBind;
    public CommonPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(setLayoutId(), container, false);
        mBind=  ButterKnife.bind(this,inflate);
        return inflate;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mModel=setModel();
        mPresenter = new CommonPresenter(this, mModel);
        setView();
        setData();
    }

    public abstract void setData();

    public abstract void setView();

    public abstract M setModel();

    public abstract int setLayoutId();

    public abstract void netSuccess(int whichApi, Object[] pD);

    public void netFailed(int whichApi, Throwable pThrowable){}



    @Override
    public void onSuccess(int whichApi, Object[] pD) {
        netSuccess(whichApi,pD);
    }

    @Override
    public void onFailed(int whichApi, Throwable pThrowable) {
        showLog("net work error: "+whichApi+"error content"+ pThrowable != null && !TextUtils.isEmpty(pThrowable.getMessage()) ? pThrowable.getMessage() : "不明错误类型");
        netFailed(whichApi,pThrowable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.clear();
        if (mBind!=null)mBind.unbind();
    }
}
