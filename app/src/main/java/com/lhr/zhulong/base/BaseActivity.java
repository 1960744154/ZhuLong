package com.lhr.zhulong.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lhr.frame.LoadTypeConfig;
import com.lhr.zhulong.interfaces.DataListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class BaseActivity extends AppCompatActivity {

    public Application1907 mApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (Application1907) getApplication();
        Log.i("1111111111111", this.getClass().getName());
    }

    /**
     * recyclerview在整个项目中使用比较频繁，将公共代码进行抽取
     * @param pRecyclerView 要操作的recyclerview
     * @param pRefreshLayout 如果有刷新和加载更多的问题，所使用的smartRefreshLayout
     * @param pDataListener 刷新和加载的监听，如果实际使用中部涉及到刷新和加载更多，直接传null
     */
    public void initRecyclerView(RecyclerView pRecyclerView, SmartRefreshLayout pRefreshLayout, final DataListener pDataListener) {
        if (pRecyclerView != null) pRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (pRefreshLayout != null && pDataListener != null) {
            pRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    pDataListener.dataType(LoadTypeConfig.REFRESH);
                }
            });
            pRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    pDataListener.dataType(LoadTypeConfig.MORE);
                }
            });
        }
    }

    public void showLog(Object content) {
        Log.e("睚眦", content.toString());
    }

    public void showToast(Object content) {
        Toast.makeText(getApplicationContext(), content.toString(), Toast.LENGTH_SHORT).show();
    }
}
