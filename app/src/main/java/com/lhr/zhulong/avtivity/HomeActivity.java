
package com.lhr.zhulong.avtivity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.lhr.zhulong.R;
import com.lhr.zhulong.base.BaseMvpActivity;
import com.lhr.zhulong.model.CommonHomeModel;


public class HomeActivity extends BaseMvpActivity<CommonHomeModel> implements NavController.OnDestinationChangedListener {


    public NavController mProjectController;

    @Override
    public CommonHomeModel setModel() {
        return new CommonHomeModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void setUpView() {
        mProjectController = Navigation.findNavController(this, R.id.project_fragment_control);
        mProjectController.addOnDestinationChangedListener(this);
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

    }
}
