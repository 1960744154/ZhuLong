package com.lhr.zhulong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.lhr.frame.ICommonModel;
import com.lhr.zhulong.R;
import com.lhr.zhulong.adapter.MyVp_Adapter;
import com.lhr.zhulong.base.BaseMvpFragment;
import com.lhr.zhulong.datafragment.InformationFragment;
import com.lhr.zhulong.datafragment.newbestFragment;
import com.lhr.zhulong.model.DataModel;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends BaseMvpFragment {

    @BindView(R.id.tv_zl)
    TextView tvZl;
    @BindView(R.id.tv_new)
    TextView tvNew;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    @Override
    public void setData() {

    }

    @Override
    public void setView() {
        ArrayList<Fragment> list = new ArrayList<>();
            list.add(new InformationFragment());
            list.add(new newbestFragment());
        MyVp_Adapter myVp_adapter = new MyVp_Adapter(getFragmentManager(), list);
        viewPager.setAdapter(myVp_adapter);
        tvZl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        tvNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    tvZl.setEnabled(false);
                    tvNew.setEnabled(true);
                    tvZl.setTextColor(Color.BLACK);
                    tvNew.setTextColor(Color.GRAY);
                } else {
                    tvZl.setEnabled(true);
                    tvNew.setEnabled(false);
                    tvNew.setTextColor(Color.BLACK);
                    tvZl.setTextColor(Color.GRAY);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public ICommonModel setModel() {
        return new DataModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_data;
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
