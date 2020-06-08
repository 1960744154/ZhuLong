package com.lhr.zhulong.model;

import android.content.Context;

import com.lhr.frame.ApiConfig;
import com.lhr.frame.FrameApplication;
import com.lhr.frame.ICommonModel;
import com.lhr.frame.ICommonPresenter;
import com.lhr.frame.NetManger;
import com.lhr.frame.utils.ParamHashMap;
import com.lhr.zhulong.R;
import com.lhr.zhulong.base.Application1907;


public class AccountModel implements ICommonModel {
   private NetManger mManger=NetManger.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();

    @Override
    public void getData(ICommonPresenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.SEND_VERIFY:
                mManger.netWork(mManger.getService(mContext.getString(R.string.passport_openapi_user))
                        .getLoginVerify((String) params[0]), pPresenter, whichApi);
                break;
            case ApiConfig.VERIFY_LOGIN:
                mManger.netWork(mManger.getService(mContext.getString(R.string.passport_openapi_user))
                        .loginByVerify(new ParamHashMap().add("mobile",params[0])
                                .add("code",params[1])),pPresenter,whichApi);
                break;
            case ApiConfig.GET_HEADER_INFO:
                String uid = FrameApplication.getFrameApplication().getLoginInfo().getUid();
                mManger.netWork(mManger.getService(mContext.getString(R.string.passport_api)).getHeaderInfo(new ParamHashMap().add("zuid",uid).add("uid",uid)),pPresenter,whichApi);
                break;
        }
    }
}
