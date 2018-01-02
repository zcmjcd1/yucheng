package com.dazhentech.faithchallengea;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dazhentech.faithchallengea.challenge.ChallengeActivity;
import com.dazhentech.faithchallengea.momentsfragment.MomentsFragment;
import com.dazhentech.faithchallengea.momentsfragment.listfragment.MomentsListFragment;
import com.dazhentech.faithchallengea.profilefragment.ProfileFragment;

public class MainActivity extends BaseActivity implements MomentsFragment.OnFragmentInteractionListener,ProfileFragment.OnFragmentInteractionListener,
        MomentsListFragment.OnFragmentInteractionListener{
    private ImageView flameStart,moments,profile;
    private FrameLayout myContainer;
    private MomentsFragment momentsFragment;
    private ProfileFragment profileFragment;
    private FragmentManager manager;

    @Override
    public void widgetClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (v.getId()){
            case R.id.start_challenge:
                startActivity(ChallengeActivity.class);
                break;
            case R.id.world_moments:
                selected();
                moments.setSelected(true);
                if(momentsFragment==null){
                    momentsFragment = new MomentsFragment();
                    transaction.add(R.id.fragment_container,momentsFragment);
                }else{
                    transaction.show(momentsFragment);
                }
                transaction.commit();
                break;
            case R.id.my_profile:
                selected();
                profile.setSelected(true);
                if(profileFragment==null){
                    profileFragment = new ProfileFragment();
                    transaction.add(R.id.fragment_container,profileFragment);
                }else{
                    transaction.show(profileFragment);
                }
                transaction.commit();
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initParms(Bundle parms) {
        setSteepStatusBar(false);
        setAllowFullScreen(false);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        flameStart =  (ImageView) findViewById(R.id.start_challenge);
        moments = (ImageView) findViewById(R.id.world_moments);
        profile = (ImageView) findViewById(R.id.my_profile);
        myContainer = (FrameLayout) findViewById(R.id.fragment_container);


    }
    //重置选中状态
    public void selected(){
        flameStart.setSelected(false);
        moments.setSelected(false);
        profile.setSelected(false);
    }
    //隐藏所有fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(momentsFragment!=null){
            transaction.hide(momentsFragment);
        }
        if(profileFragment!=null){
            transaction.hide(profileFragment);
        }
    }

    @Override
    public void setListener() {
        flameStart.setOnClickListener(this);
        moments.setOnClickListener(this);
        profile.setOnClickListener(this);

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
