package com.dazhentech.faithchallengea.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhaochangming on 2017/12/24.
 */

public class ChallengeRecord extends BmobObject {
    private String expect_gain;
    private String real_gain;
    private String content;
    private Integer cost;
    private Boolean result;
    private MidTermGoal midterm_goal;
    private String day;
    private Integer period_minute;
    private ChallengeHall hall;

    public String getExpect_gain() {
        return expect_gain;
    }

    public void setExpect_gain(String expect_gain) {
        this.expect_gain = expect_gain;
    }

    public String getReal_gain() {
        return real_gain;
    }

    public void setReal_gain(String real_gain) {
        this.real_gain = real_gain;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public MidTermGoal getMidterm_goal() {
        return midterm_goal;
    }

    public void setMidterm_goal(MidTermGoal midterm_goal) {
        this.midterm_goal = midterm_goal;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getPeriod_minute() {
        return period_minute;
    }

    public void setPeriod_minute(Integer period_minute) {
        this.period_minute = period_minute;
    }

    public ChallengeHall getHall() {
        return hall;
    }

    public void setHall(ChallengeHall hall) {
        this.hall = hall;
    }
}
