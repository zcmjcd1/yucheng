package com.dazhentech.faithchallengea.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dazhentech.faithchallengea.R;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener{
    Button mJoinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("1233333333333333");
        setContentView(R.layout.activity_guide);
        mJoinButton = (Button) findViewById(R.id.join_myfaith);
        mJoinButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.join_myfaith:
                Intent intent = new Intent(GuideActivity.this, RegisterLoginActivity.class );
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
