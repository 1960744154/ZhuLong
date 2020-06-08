package com.lhr.zhulong.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lhr.frame.ICommonModel;
import com.lhr.zhulong.R;
import com.lhr.zhulong.adapter.MyVp_Adapter;
import com.lhr.zhulong.base.BaseMvpFragment;
import com.lhr.zhulong.course_fragment.PremiumFragment;
import com.lhr.zhulong.model.CouresModel;

import java.util.ArrayList;

import butterknife.BindView;

public class CourseFragment extends BaseMvpFragment {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    public void setData() {

    }

    @Override
    public void setView() {
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new PremiumFragment(3));
        list.add(new PremiumFragment(1));
        list.add(new PremiumFragment(2));
        MyVp_Adapter myVp_adapter = new MyVp_Adapter(getActivity().getSupportFragmentManager(), list);
       viewPager.setAdapter(myVp_adapter);
       tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("训练营");
        tabLayout.getTabAt(1).setText("精品课");
        tabLayout.getTabAt(2).setText("公开课");


    }

    @Override
    public ICommonModel setModel() {
        return new CouresModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_course;
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }
}
