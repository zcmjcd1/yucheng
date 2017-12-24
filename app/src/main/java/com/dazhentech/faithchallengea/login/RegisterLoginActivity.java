package com.dazhentech.faithchallengea.login;



import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dazhentech.faithchallengea.MainActivity;
import com.dazhentech.faithchallengea.R;
import com.dazhentech.faithchallengea.bean.AppUser;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

public class RegisterLoginActivity extends BaseActivityLogin {
    private Button getVerifyCode;
    private TextInputEditText phone;
    private TextInputEditText code;
    private Button next;
    String phonenum = "";

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.get_phone_code_btn:
                BmobSMS.requestSMSCode(phone.getText().toString(), "challenge", new QueryListener<Integer>() {

                    @Override
                    public void done(Integer smsId, BmobException ex) {
                        if (ex == null) {//验证码发送成功
                            Log.i(TAG, "短信id：" + smsId);//用于后续的查询本次短信发送状态
                        }
                    }
                });
                break;
            case R.id.btn_next_to_set_password:
                phonenum = phone.getText().toString();
//                BmobUser.signOrLoginByMobilePhone(phonenum, code.getText().toString(), new LogInListener<AppUser>() {
//                } {
//                    @Override
//                    public void done(AppUser user,BmobException e) {
//                        if (e == null) {// 短信验证码已验证成功
//                            Log.i(TAG, "bmob验证通过");
//                            Bundle bundle = new Bundle();
//                            bundle.putString("phoneNumber", phonenum);
//                            startActivity(SetPasswordActivity.class, bundle);
//                        } else {
//                            Log.i(TAG, "bmob验证失败：code ="
//                                    + e.getErrorCode() + ",msg = "
//                                    + e.getLocalizedMessage());
//                            showToast("验证失败，请输入正确验证码！");
//                        }
//                    }
//                });
                BmobUser.signOrLoginByMobilePhone(phonenum, code.getText().toString(), new LogInListener<AppUser>() {
                    @Override
                    public void done(AppUser appUser, BmobException e) {
                        if(appUser!=null){
                            Log.i(TAG,"用户登陆成功");
                            startActivity(MainActivity.class);
                        }
                    }
                });
                break;
            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(View view) {
        getVerifyCode = (Button)findViewById(R.id.get_phone_code_btn);
        phone = (TextInputEditText)findViewById(R.id.phone_register);
        code = (TextInputEditText)findViewById(R.id.phone_verify_code);
        next = (Button)findViewById(R.id.btn_next_to_set_password);
    }

    @Override
    public void setListener() {
        getVerifyCode.setOnClickListener(this);
        next.setOnClickListener(this);

    }

    @Override
    public void doBusiness(Context mContext) {


    }
}
