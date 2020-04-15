package com.wangky.rnandroid;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

public class MyApplication extends Application implements ReactApplication {
    public static Context appContext;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appContext = getApplicationContext();

    }


    public static MyApplication getInstance(){

        return instance;
    }


    private ReactNativeHost reactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(new MainReactPackage());
        }


        @Nullable
        @Override
        protected String getJSBundleFile() {

            String path = Environment.getExternalStorageDirectory().getAbsolutePath() +File.separator + "rn/index.android.bundle";
            File file = new File(path);

            if(file !=null && file.exists()){
                return path;
            }

            return super.getJSBundleFile();
        }
    };


    @Override
    public ReactNativeHost getReactNativeHost() {
        return reactNativeHost;
    }




}
