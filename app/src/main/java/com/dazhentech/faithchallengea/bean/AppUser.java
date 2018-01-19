package com.dazhentech.faithchallengea.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by zhaochangming on 2017/12/13.
 */

public class AppUser extends BmobUser {
    //毅力点，仿跳一跳的计分方式，完成一次记1点，连续第二次两点，连续完成则第三次4点，第四次6点第五次8点，可以靠消耗毅力点来抽鼓励金，鼓励金=报名费（1-平台抽取百分比），
    //平台抽取百分比为15+35x(失败百分比)，也就是说平台最少抽走15%最多抽50%
    //10分钟一次的阳光，和1天一次的雨露，一周一次的世纪灵光和21天一次的混沌灵液，app显示有多少人在等待下一次阳光/雨露/世纪灵光/混沌灵液
    //想获取阳光雨露世纪灵光混沌灵液需要用到毅力点，无上限，例如，下一次阳光有100人在等待，总毅力点为1000，100人所使用的毅力点各不相同，如果全部都完成了目标则，85元按所压毅力点比例分配，例如用户
    //a使用了20点来获取下一次阳光，则他的收入就是1.65赚到了6.5而想要快速获取到20点毅力点就需要连续6次完成，用时2小时6分钟左右
    //雨露除了人数x85%的鼓励金外还包括平台当天30%的利润，世纪灵光  平台计算出奖励金额，需要作到至少不亏损
    //炼体，炼心，练秘术，一次body+1体质，一次心灵+1精神，一次专业+1法力
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
