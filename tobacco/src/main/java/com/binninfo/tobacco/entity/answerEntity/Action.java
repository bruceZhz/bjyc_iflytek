package com.binninfo.tobacco.entity.answerEntity;

import com.binninfo.tobacco.entity.Map;

public class Action {

    private String type;
    private Map address;
    private String action;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map getAddress() {
        return address;
    }

    public void setAddress(Map address) {
        this.address = address;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
