package com.wangky.rnandroid;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

public class MyRnActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {


    private ReactRootView mReactRootView;

    private ReactInstanceManager mReactInstanceManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SoLoader.init(this,false);
        mReactRootView = new ReactRootView(this);
//        mReactInstanceManager = ReactInstanceManager.builder()
//                        .setApplication(getApplication())
//                        .setCurrentActivity(this)
//                        .setBundleAssetName("index.android.bundle")
//                        .setJSMainModulePath("index")
//                        .addPackage(new MainReactPackage())
//                        .setUseDeveloperSupport(BuildConfig.DEBUG)
//                        .setInitialLifecycleState(LifecycleState.RESUMED)
//                        .build();

        mReactInstanceManager =MyApplication.getInstance().getReactNativeHost().getReactInstanceManager();


        mReactRootView.startReactApplication(mReactInstanceManager,"MyReactNativeApp",null);


        setContentView(mReactRootView);
    }


    @Override
    protected void onPause() {
        super.onPause();

        if(mReactInstanceManager != null){
            mReactInstanceManager.onHostPause(this);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(mReactInstanceManager != null){
            mReactInstanceManager.onHostResume(this,this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mReactInstanceManager !=null){
            mReactInstanceManager.onHostDestroy(this);
        }

        if(mReactRootView !=null){
            mReactRootView.unmountReactApplication();
        }

    }


    @Override
    public void onBackPressed() {

        if (mReactInstanceManager != null){

            mReactInstanceManager.onBackPressed();
        }else {
            super.onBackPressed();

        }

    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }



    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
