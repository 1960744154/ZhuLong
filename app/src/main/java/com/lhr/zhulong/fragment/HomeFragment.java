package com.lhr.zhulong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;


import com.lhr.data.SpecialtyChooseEntity;
import com.lhr.frame.constants.ConstantKey;
import com.lhr.utils.newAdd.SharedPrefrenceUtils;
import com.lhr.zhulong.R;
import com.lhr.zhulong.avtivity.SubjectActivity;
import com.lhr.zhulong.base.Application1907;
import com.lhr.zhulong.base.BaseMvpFragment;
import com.lhr.zhulong.design.BottomTabView;
import com.lhr.zhulong.model.MainPageModel;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseMvpFragment<MainPageModel> implements BottomTabView.OnBottomTabClickCallBack, NavController.OnDestinationChangedListener {
    @BindView(R.id.top_ll)
    LinearLayout topLl;
    @BindView(R.id.select_subject)
    TextView selectSubject;
    private List<Integer> normalIcon = new ArrayList<>();//为选中的icon
    private List<Integer> selectedIcon = new ArrayList<>();// 选中的icon
    private List<String> tabContent = new ArrayList<>();//tab对应的内容
    protected NavController mHomeController;
    private final int MAIN_PAGE = 1, COURSE = 2, VIP = 3, DATA = 4, MINE = 5;


    @Override
        public void setData() {
        mHomeController = Navigation.findNavController(getView().findViewById(R.id.home_fragment_container));
        mHomeController.addOnDestinationChangedListener(this);
    }


    @Override
    public void setView() {
        //sp进行读取
        SpecialtyChooseEntity.DataBean selectedInfo = Application1907.getFrameApplication().getSelectedInfo();
        if (SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.SUBJECT_SELECT)!=null){
            SpecialtyChooseEntity.DataBean bean = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.SUBJECT_SELECT);
            selectSubject.setText(bean.getSpecialty_name());
        }


        BottomTabView tabView = getView().findViewById(R.id.bottom_tab_view);
        Collections.addAll(normalIcon, R.drawable.main_page_view, R.drawable.course_view, R.drawable.vip_view, R.drawable.data_view, R.drawable.mine_view);
        Collections.addAll(selectedIcon, R.drawable.main_selected, R.drawable.course_selected, R.drawable.vip_selected, R.drawable.data_selected, R.drawable.mine_selected);
        Collections.addAll(tabContent, "主页", "课程", "VIP", "资料", "我的");
        tabView.setResource(normalIcon, selectedIcon, tabContent);
        tabView.setOnBottomTabClickCallBack(this);
        selectSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SubjectActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public MainPageModel setModel() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }

    @Override
    public void netFailed(int whichApi, Throwable pThrowable) {

    }

    @Override
    public void clickTab(int tab) {
        switch (tab) {
            case MAIN_PAGE:
                mHomeController.navigate(R.id.mainPageFragment);
                topLl.setVisibility(View.VISIBLE);
                break;
            case COURSE:
                mHomeController.navigate(R.id.courseFragment);
                topLl.setVisibility(View.VISIBLE);
                break;
            case VIP:
                mHomeController.navigate(R.id.vipFragment);
                topLl.setVisibility(View.VISIBLE);
                break;
            case DATA:
                mHomeController.navigate(R.id.dataFragment);
                topLl.setVisibility(View.VISIBLE);
                break;
            case MINE:
                mHomeController.navigate(R.id.mineFragment);
                topLl.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        normalIcon.clear();
        selectedIcon.clear();
        tabContent.clear();
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        showLog(destination.getLabel().toString());
    }
}
