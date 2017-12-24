package com.dazhentech.faithchallengea.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhaochangming on 2017/12/24.
 */

public class ChallengeHall extends BmobObject {
    private String date;
    private Integer human_count;
    private Integer wemen_count;
    private Integer cost_sum;
    private Integer success_count;
    private Integer failure_count;
    private Integer reward_pool;
    private Integer app_gain;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getHuman_count() {
        return human_count;
    }

    public void setHuman_count(Integer human_count) {
        this.human_count = human_count;
    }

    public Integer getWemen_count() {
        return wemen_count;
    }

    public void setWemen_count(Integer wemen_count) {
        this.wemen_count = wemen_count;
    }

    public Integer getCost_sum() {
        return cost_sum;
    }

    public void setCost_sum(Integer cost_sum) {
        this.cost_sum = cost_sum;
    }

    public Integer getSuccess_count() {
        return success_count;
    }

    public void setSuccess_count(Integer success_count) {
        this.success_count = success_count;
    }

    public Integer getFailure_count() {
        return failure_count;
    }

    public void setFailure_count(Integer failure_count) {
        this.failure_count = failure_count;
    }

    public Integer getReward_pool() {
        return reward_pool;
    }

    public void setReward_pool(Integer reward_pool) {
        this.reward_pool = reward_pool;
    }

    public Integer getApp_gain() {
        return app_gain;
    }

    public void setApp_gain(Integer app_gain) {
        this.app_gain = app_gain;
    }
}
