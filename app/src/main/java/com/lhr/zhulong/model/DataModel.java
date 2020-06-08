package com.lhr.zhulong.model;


import android.content.Context;


import com.lhr.frame.ApiConfig;
import com.lhr.frame.ICommonModel;
import com.lhr.frame.ICommonPresenter;
import com.lhr.frame.NetManger;
import com.lhr.zhulong.base.Application1907;

import java.util.Map;

public class DataModel implements ICommonModel {
    NetManger manger=NetManger.getInstance();
    private Context context= Application1907.get07ApplicationContext();
    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi){
          case   ApiConfig.GET_INFORMATION_INFO:
              Map<String, Object> infom = (Map<String, Object>) params[0];
              manger.netWork(manger.getService(" https://bbs.zhulong.com").getInformation(infom),pPresenter,whichApi);
       break;
            case ApiConfig.GET_NEWBEST_INFO:
                Map<String,Object>newbest=(Map<String,Object>)params[0];
                manger.netWork(manger.getService("https://bbs.zhulong.com/").getnewbest(newbest),pPresenter,whichApi);
                break;
        }
    }
}
