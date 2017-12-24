package com.dazhentech.faithchallengea.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhaochangming on 2017/12/24.
 */

public class UserAccountBill extends BmobObject {
    private UserAccount user_account;
    private Integer balance;
    private Integer bill_rmb;
    private Integer bill_type;//0挑战金，1奖金，2，充值，3返还挑战金
    private ChallengeRecord challenge_record;

    public UserAccount getUser_account() {
        return user_account;
    }

    public void setUser_account(UserAccount user_account) {
        this.user_account = user_account;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getBill_rmb() {
        return bill_rmb;
    }

    public void setBill_rmb(Integer bill_rmb) {
        this.bill_rmb = bill_rmb;
    }

    public Integer getBill_type() {
        return bill_type;
    }

    public void setBill_type(Integer bill_type) {
        this.bill_type = bill_type;
    }

    public ChallengeRecord getChallenge_record() {
        return challenge_record;
    }

    public void setChallenge_record(ChallengeRecord challenge_record) {
        this.challenge_record = challenge_record;
    }
}
