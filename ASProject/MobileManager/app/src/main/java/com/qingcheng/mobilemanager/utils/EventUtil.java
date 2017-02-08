package com.qingcheng.mobilemanager.utils;

import java.io.Serializable;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : RGK
 * Author     : qi.guan
 * Date       : 2016/11/28 17:40
 */

public class EventUtil implements Serializable{

    private String strMsg;
    private int intMsg;
    private Boolean booleanMsg;

    public String getStrMsg() {
        return this.strMsg;
    }

    public EventUtil(String strMsg) {
        this.strMsg = strMsg;
    }

    public int getIntMsg() {
        return this.intMsg;
    }

    public EventUtil(int intMsg) {
        this.intMsg = intMsg;
    }

    public Boolean getBooleanMsg() {
        return this.booleanMsg;
    }

    public EventUtil(Boolean booleanMsg) {
        this.booleanMsg = booleanMsg;
    }

}
