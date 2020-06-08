package com.lhr.zhulong.model;

import android.content.Context;

import com.lhr.frame.ApiConfig;
import com.lhr.frame.ICommonModel;
import com.lhr.frame.ICommonPresenter;
import com.lhr.frame.NetManger;
import com.lhr.frame.utils.ParamHashMap;
import com.lhr.zhulong.R;
import com.lhr.zhulong.base.Application1907;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FirstModel implements ICommonModel {
    NetManger netManger=NetManger.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();

    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi){
            case ApiConfig.GET_BANNER_LIVE:
                ParamHashMap  param = (ParamHashMap) params[0];
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody build = new FormBody.Builder()
                        .add("pro", (String) param.get("pro"))
                        .add("uid", (String) param.get("uid"))
                        .build();
                Request build1 = new Request.Builder()
                        .url("https://edu.zhulong.com/openapi/lesson/get_new_vip")
                        .post(build)
                        .build();
                okHttpClient.newCall(build1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        pPresenter.getData(whichApi,string);
                    }
                });
                break;
            case ApiConfig.GET_List_LIVE:
                int loadType = (int) params[0];
                Map<String,Object> paramHashMap = (ParamHashMap) params[1];
                netManger.netWork( netManger.getService(mContext.getString(R.string.edu_openapi)).getMainPageList(paramHashMap), pPresenter, whichApi,loadType);
                break;
        }
    }
}
