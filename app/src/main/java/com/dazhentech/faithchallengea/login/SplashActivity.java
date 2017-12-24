package com.dazhentech.faithchallengea.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dazhentech.faithchallengea.MainActivity;
import com.dazhentech.faithchallengea.R;
import com.dazhentech.faithchallengea.bean.AppUser;

import cn.bmob.v3.BmobUser;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppUser userinfo =  BmobUser.getCurrentUser(AppUser.class);
        if(userinfo!=null){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }else {
            try{
                Intent intent = new Intent("com.dazhentech.faithchallengea.ACTION_START");
                startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        finish();
    }
}
