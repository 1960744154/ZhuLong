package com.lhr.zhulong.model;


import android.content.Context;


import com.lhr.frame.ApiConfig;
import com.lhr.frame.ICommonModel;
import com.lhr.frame.ICommonPresenter;
import com.lhr.frame.NetManger;
import com.lhr.zhulong.base.Application1907;

import java.util.Map;

public class CouresModel implements ICommonModel {
    private NetManger manger=NetManger.getInstance();
    private Context context = Application1907.get07ApplicationContext();

    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi){
            case ApiConfig.GET_TRAINING_INFO:
               Map<String,Object> m= (Map<String, Object>) params[0];
                manger.netWork(manger.getService( "https://edu.zhulong.com/openapi/").getTrainInfo(m),pPresenter,whichApi);
                break;
        }
    }
}
