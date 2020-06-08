package com.lhr.zhulong.model;


import com.lhr.frame.ICommonModel;
import com.lhr.frame.ICommonPresenter;
import com.lhr.frame.NetManger;

import java.util.HashMap;


public class MainPageModel implements ICommonModel {
    private NetManger manger=NetManger.getInstance();
    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
//        manger.netWork(manger.getService("https://baidu/com/").getHeaderInfo(new HashMap<>()), pPresenter, whichApi);
//        manger.netWork(manger.getService("https://baidu/com/com/").getHeaderInfo(new HashMap<>()), pPresenter, whichApi);
    }
}
