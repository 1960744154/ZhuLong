package com.lhr.zhulong.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lhr.data.BaseInfo;
import com.lhr.data.LiveBean;
import com.lhr.data.LiveListBean;
import com.lhr.data.MainPageBean;
import com.lhr.data.SpecialtyChooseEntity;
import com.lhr.frame.ApiConfig;
import com.lhr.frame.ICommonModel;
import com.lhr.frame.LoadTypeConfig;
import com.lhr.frame.utils.ParamHashMap;
import com.lhr.utils.GsonUtil;
import com.lhr.zhulong.R;
import com.lhr.zhulong.adapter.LiveAdapter;
import com.lhr.zhulong.adapter.MainPageListAdapter;
import com.lhr.zhulong.base.Application1907;
import com.lhr.zhulong.base.BaseMvpFragment;
import com.lhr.zhulong.interfaces.DataListener;
import com.lhr.zhulong.model.FirstModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainPageFragment extends BaseMvpFragment {
    @BindView(R.id.main_page_banner)
    Banner mainPageBanner;
    @BindView(R.id.live_group)
    RecyclerView liveGroup;
    @BindView(R.id.main_page_recycler)
    RecyclerView mainPageRecycler;
    @BindView(R.id.main_page_smart)
    SmartRefreshLayout mainPageSmart;
    @BindView(R.id.zhibo)
    ConstraintLayout zhibo;
    @BindView(R.id.views)

    View views;
    private String specialty_id;
    private LiveAdapter liveAdapter;
    private int page;
    private MainPageListAdapter mainPageListAdapter;


    @Override
    public void setData() {
        ParamHashMap add = new ParamHashMap().add("pro", specialty_id).add("more_live", 1).add("is_new", 1).add("new_banner", 1);
        mPresenter.getData(ApiConfig.GET_BANNER_LIVE, add);

        ParamHashMap add1 = new ParamHashMap().add("specialty_id", specialty_id).add("page", page).add("limit", 10);
        mPresenter.getData(ApiConfig.GET_List_LIVE, add1);

        initRecyclerView(mainPageRecycler, mainPageSmart, new DataListener() {
            @Override
            public void dataType(int mode) {
                if (mode == LoadTypeConfig.REFRESH) {
                    page = 0;
                    ParamHashMap add1 = new ParamHashMap().add("specialty_id", specialty_id).add("page", page).add("limit", 10);
                    mPresenter.getData(ApiConfig.GET_List_LIVE, LoadTypeConfig.REFRESH, add1);
                } else {
                    page++;
                    ParamHashMap add1 = new ParamHashMap().add("specialty_id", specialty_id).add("page", page).add("limit", 10);
                    mPresenter.getData(ApiConfig.GET_List_LIVE, LoadTypeConfig.MORE, add1);
                }
            }
        });
    }

    @Override
    public void setView() {
        ButterKnife.bind(getActivity());
        SpecialtyChooseEntity.DataBean selectedInfo = Application1907.getFrameApplication().getSelectedInfo();
        specialty_id = selectedInfo.getSpecialty_id();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        liveGroup.setLayoutManager(linearLayoutManager);
        liveAdapter = new LiveAdapter(getActivity());
        liveGroup.setAdapter(liveAdapter);

        mainPageRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainPageListAdapter = new MainPageListAdapter(getActivity());
        mainPageRecycler.setAdapter(mainPageListAdapter);
    }

    @Override
    public ICommonModel setModel() {
        return new FirstModel();
    }


    @Override
    public int setLayoutId() {
        return R.layout.fragment_main_page;
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.GET_BANNER_LIVE:
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switch (whichApi) {
                            case ApiConfig.GET_BANNER_LIVE:
                                String s = (String) pD[0];

                                JSONObject object = null;
                                try {
                                    object = new JSONObject(s).optJSONObject("result");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                JSONArray object_list = object.optJSONArray("Carousel");
                                List<MainPageBean.CarouselBean> list = GsonUtil.jsonToList(object_list.toString(), MainPageBean.CarouselBean.class);
                                if (list != null && list.size() != 0) {
                                    mainPageBanner.setImages(list).setImageLoader(new ImageLoader() {
                                        @Override
                                        public void displayImage(Context context, Object path, ImageView imageView) {
                                            MainPageBean.CarouselBean path1 = (MainPageBean.CarouselBean) path;
                                            Glide.with(context).load(path1.getImg()).into(imageView);
                                        }
                                    }).start();
                                }

                                List<LiveBean> liveingEntitys = GsonUtil.jsonToList(object.optString("live"), LiveBean.class);
                                if (liveingEntitys != null && liveingEntitys.size() != 0) {
                                    liveAdapter.addList(liveingEntitys);
                                } else {
                                    zhibo.setVisibility(View.GONE);
                                    views.setVisibility(View.GONE);
                                }
                                break;
                        }
                    }
                });
                break;

            case ApiConfig.GET_List_LIVE:

                Integer[] integers = (Integer[]) pD[1];

                int loatType = integers[0].intValue();

                BaseInfo<List<LiveListBean>> bean = (BaseInfo<List<LiveListBean>>) pD[0];
                List<LiveListBean> result = bean.result;
                if (loatType == LoadTypeConfig.NORMAL) {
                    mainPageListAdapter.addList(result);
                } else if (loatType == LoadTypeConfig.MORE) {
                    mainPageSmart.finishLoadMore();
                    mainPageListAdapter.addList(result);
                }
                break;
        }
    }
}
