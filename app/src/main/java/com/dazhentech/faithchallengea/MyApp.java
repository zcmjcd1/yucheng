package com.dazhentech.faithchallengea;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by zhaochangming on 2017/12/13.
 */

public class MyApp extends Application {
    private static final String BMOB_APP_ID = "7175accbb6dd46bfae4493ef1a7cbe33";

    @Override
    public void onCreate() {
        super.onCreate();
        try{
            System.out.println("11111111111111111");
            Bmob.initialize(this, BMOB_APP_ID);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}


