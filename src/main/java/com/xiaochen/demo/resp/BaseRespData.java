package com.xiaochen.demo.resp;

import java.io.Serializable;

public class BaseRespData implements Serializable {

    private String tips;
    private String curTime;

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getCurTime() {
        return curTime;
    }

    public void setCurTime(String curTime) {
        this.curTime = curTime;
    }
}
