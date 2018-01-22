package com.dazhentech.faithchallengea.challenge;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dazhentech.faithchallengea.BaseActivity;
import com.dazhentech.faithchallengea.R;
import com.dazhentech.faithchallengea.bean.AppUser;
import com.dazhentech.faithchallengea.bean.ChallengeRecord;
import com.dazhentech.faithchallengea.challenge.countdownfragment.BackHandledInterface;
import com.dazhentech.faithchallengea.challenge.countdownfragment.CountDownFragment;
import com.dazhentech.faithchallengea.challenge.failfragment.FailFragment;
import com.dazhentech.faithchallengea.challenge.finishfragment.FinishFragment;
import com.dazhentech.faithchallengea.challenge.startfragment.StartFragment;
import com.dazhentech.faithchallengea.challenge.succeedfragment.SucceedFragment;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class ChallengeActivity extends BaseActivity implements CountDownFragment.OnFragmentInteractionListener,StartFragment.OnFragmentInteractionListener,
FailFragment.OnFragmentInteractionListener,SucceedFragment.OnFragmentInteractionListener, BackHandledInterface, FinishFragment.OnFragmentInteractionListener {
    ActionBar mActionBar;
    StartFragment startFragment;
    CountDownFragment countDownFragment;
    FailFragment failFragment;
    SucceedFragment succeedFragment;
    FinishFragment finishFragment;
    FragmentManager fragmentManager;
    SharedPreferences config;
    ChallengeRecord challengeRecord;
    AppUser appUser;

//    FragmentTransaction ft;

    boolean pendingChallenge = false;
    TextView thisTrialScore;



    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (!pendingChallenge){
            ft.replace(R.id.challenge_container,getStartFragment());
        }
        ft.commit();

    }
    public StartFragment getStartFragment(){
        if (startFragment==null){
            startFragment = new StartFragment();
        }
        return startFragment;
    }

    @Override
    public void initParms(Bundle parms) {
        setSteepStatusBar(false);
        setAllowFullScreen(false);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        config = getApplicationContext().getSharedPreferences("Mytrials",Context.MODE_PRIVATE);

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_challenge;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onTryAgainClicked() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        startFragment = new StartFragment();
        ft.replace(R.id.challenge_container,startFragment);
        ft.commit();
    }

    @Override
    public void onTimeout() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        SharedPreferences.Editor editor = config.edit();
        if(config.getBoolean("fristfail",true)){
            failFragment = new FailFragment();
            ft.replace(R.id.challenge_container,failFragment);
            ft.commit();
            challengeRecord.setResult(false);
            challengeRecord.save();
            editor.putBoolean("firstfail",false);
        }else{
            finishFragment = new FinishFragment();
            ft.replace(R.id.challenge_container,finishFragment);
            ft.commit();
        }

        editor.putInt("thistrialstatus",2);
        editor.apply();
    }

    public void updateThisTrialSccore(){
        try{
            int myScore = config.getInt("thistrialsum",0);
            thisTrialScore = (TextView) findViewById(R.id.challenge_score);
            thisTrialScore.setText(String.valueOf(myScore));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTrialFinish() {
        updateThisTrialSccore();
        if(challengeRecord!=null){
            System.out.println("1111111111+"+challengeRecord.getDay());
        }
        challengeRecord.setScore(config.getInt("thistrialsum",0));
        setThreeProperties();
        challengeRecord.setResult(true);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        succeedFragment = new SucceedFragment();
        ft.replace(R.id.challenge_container,succeedFragment);
        ft.commit();
    }

    @Override
    public void onRepeatTrial() {
        updateThisTrialSccore();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        startFragment = new StartFragment();
        ft.replace(R.id.challenge_container,startFragment);
        ft.commit();
        setThreeProperties();
    }
    public void setThreeProperties(){
        SharedPreferences.Editor editor = config.edit();
        editor.putInt("thistrialstatus",1);
        System.out.println("currenttag"+config.getString("currenttag",""));
        switch(config.getString("currenttag","")){
            case "body":
                editor.putInt("thistrialbody",config.getInt("thistrialbody",0)+1);
                break;
            case "heart":
                System.out.println("5555555555555555");
                editor.putInt("thistrialheart",config.getInt("thistrialheart",0)+1);
                break;
            case "profession":
                editor.putInt("thistrialprofession",config.getInt("thistrialprofession",0)+1);
                break;
        }
        editor.apply();
    }

    private Handler handler = new Handler();

    @Override
    public void onButtonClicked(String selectedTag) {
        final SharedPreferences.Editor editor = config.edit();
        if (config.getBoolean("firststart",true)){
            editor.putBoolean("lastcombo",false);
            editor.putInt("lastadd",0);
            editor.putInt("thistrialsum",0);
            editor.putInt("thistrialstatus",0);
            editor.putLong("trailstarttime",System.currentTimeMillis());
            editor.putInt("trialindex",1);
            editor.putString("trialgain","");
            editor.putBoolean("firststart",false);
            editor.putBoolean("firstfail",true);
            editor.putInt("thistrialbody",0);
            editor.putInt("thistrialheart",0);
            editor.putInt("thistrialprofession",0);
        }
        editor.putString("currenttag",selectedTag);

        editor.apply();
        pendingChallenge = true;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        countDownFragment = new CountDownFragment();
        ft.replace(R.id.challenge_container,countDownFragment);
        ft.commit();
        challengeRecord = new ChallengeRecord();
        challengeRecord.setCost(100);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
        System.out.println("格式化后的日期"+sdf.format(d));
        challengeRecord.setUser(BmobUser.getCurrentUser(AppUser.class));
        challengeRecord.setDay(sdf.format(d));
        challengeRecord.setPeriod_minute(12000);
        challengeRecord.setScore(0);
        challengeRecord.setTag(selectedTag);
//        challengeRecord.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if(e==null){
//                    final String str = s;
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            editor.putString("thistrialid",str);
//                        }
//                    });
//                }
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if (countDownFragment == null) {
            super.onBackPressed();
        } else {
            //处理
            showNormalDialog();
        }
    }

    @Override
    public void setSelectedFragment(CountDownFragment selectedFragment) {
        this.countDownFragment = selectedFragment;
    }

    public void showNormalDialog(){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(ChallengeActivity.this);
        normalDialog.setIcon(R.mipmap.delete);
        normalDialog.setTitle("退出挑战");
        normalDialog.setMessage("退出不退还押金，是否退出？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        finish();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();

    }

    @Override
    public void onTimeReached() {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        finishFragment = new FinishFragment();
        ft.replace(R.id.challenge_container,finishFragment);
        ft.commit();
    }

    @Override
    public void onSucceedSubmitButtonClick(String gain) {
        challengeRecord.setReal_gain(gain);
//        System.out.println(challengeRecord.getTag()+challengeRecord.getReal_gain()+challengeRecord.getDay()+String.valueOf(challengeRecord.getResult()));
        challengeRecord.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    System.out.println("创建数据成功：" + s);
                }else{
                    System.out.println("bmob"+"失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
        appUser = BmobUser.getCurrentUser(AppUser.class);
        appUser.increment("body",config.getInt("thistrialbody",0));
        appUser.increment("heart",config.getInt("thistrialheart",0));
        appUser.increment("profession",config.getInt("thistrialprofession",0));
        SharedPreferences.Editor editor = config.edit();
        editor.putBoolean("firststart",true);
        editor.apply();
        appUser.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","成功");
                    finish();
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });


    }
}
