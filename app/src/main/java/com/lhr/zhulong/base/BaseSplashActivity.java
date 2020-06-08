package com.lhr.zhulong.base;

import android.widget.ImageView;
import android.widget.TextView;


import com.lhr.data.Device;
import com.lhr.frame.FrameApplication;
import com.lhr.frame.secret.SystemUtils;
import com.lhr.utils.newAdd.NetworkUtils;
import com.lhr.zhulong.R;

import butterknife.BindView;

public abstract class BaseSplashActivity extends com.lhr.zhulong.base.BaseMvpActivity {
    @BindView(R.id.advert_image)
    public ImageView advertImage;
    @BindView(R.id.time_view)
    public TextView timeView;

    @Override
    public int setLayoutId() {
        return R.layout.activity_splash;
    }

    public void initDevice() {
        Device device = new Device();
        device.setScreenWidth(SystemUtils.getSize(this).x);
        device.setScreenHeight(SystemUtils.getSize(this).y);
        device.setDeviceName(SystemUtils.getDeviceName());
        device.setSystem(SystemUtils.getSystem(this));
        device.setVersion(SystemUtils.getVersion(this));
        device.setDeviceId(SystemUtils.getDeviceId(this));
        device.setLocalIp(NetworkUtils.getLocalIpAddress());
        FrameApplication.getFrameApplication().setDeviceInfo(device);
    }
}
