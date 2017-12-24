package com.dazhentech.faithchallengea.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by zhaochangming on 2017/12/13.
 */

public class AppUser extends BmobUser {
    private Integer age ;
    private Boolean sex ;
    private Integer level;
    private UserAccount account;
    private Integer c_count;
    private Integer c_success;
    private Integer c_failure;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
