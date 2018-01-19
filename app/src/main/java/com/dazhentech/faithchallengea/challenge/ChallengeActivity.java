package com.dazhentech.faithchallengea.challenge;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dazhentech.faithchallengea.BaseActivity;
import com.dazhentech.faithchallengea.R;
import com.dazhentech.faithchallengea.challenge.countdownfragment.BackHandledInterface;
import com.dazhentech.faithchallengea.challenge.countdownfragment.CountDownFragment;
import com.dazhentech.faithchallengea.challenge.failfragment.FailFragment;
import com.dazhentech.faithchallengea.challenge.finishfragment.FinishFragment;
import com.dazhentech.faithchallengea.challenge.startfragment.StartFragment;
import com.dazhentech.faithchallengea.challenge.succeedfragment.SucceedFragment;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

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
        failFragment = new FailFragment();
        ft.replace(R.id.challenge_container,failFragment);
        ft.commit();
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
        FragmentTransaction ft = fragmentManager.beginTransaction();
        succeedFragment = new SucceedFragment();
        ft.replace(R.id.challenge_container,succeedFragment);
        ft.commit();
    }

    @Override
    public void onRepeatTrial() {
        updateThisTrialSccore();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        countDownFragment = new CountDownFragment();
        ft.replace(R.id.challenge_container,countDownFragment);
        ft.commit();
    }

    @Override
    public void onButtonClicked(String selectedTag) {
        SharedPreferences.Editor editor = config.edit();
        editor.putBoolean("lastcombo",false);
        editor.putInt("lastadd",0);
        editor.putInt("thistrialsum",0);
        editor.apply();
        pendingChallenge = true;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        countDownFragment = new CountDownFragment();
        ft.replace(R.id.challenge_container,countDownFragment);
        ft.commit();
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
    public void onSucceedSubmitButtonClick() {
        finish();
    }
}
