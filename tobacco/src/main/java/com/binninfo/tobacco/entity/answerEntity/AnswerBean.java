package com.binninfo.tobacco.entity.answerEntity;

import com.alibaba.fastjson.JSONObject;
import com.binninfo.tobacco.entity.Goods;
import java.util.*;

public class AnswerBean {
    private Integer rc;
    private String type;
    private Action action;
    private boolean isSleep;
    private String vidoURL;
    private boolean canListen;
    private String answer;
    private Goods goods;
    private int jumpModel;
    private List<ListBean> list;

    public Integer getRc() {
        return rc;
    }

    public void setRc(Integer rc) {
        this.rc = rc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public boolean getIsSleep() {
        return isSleep;
    }

    public void setIsSleep(boolean sleep) {
        isSleep = sleep;
    }

    public String getVidoURL() {
        return vidoURL;
    }

    public void setVidoURL(String vidoURL) {
        this.vidoURL = vidoURL;
    }

    public boolean getCanListen() {
        return canListen;
    }

    public void setCanListen(boolean canListen) {
        this.canListen = canListen;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getJumpModel() {
        return jumpModel;
    }

    public void setJumpModel(int jumpModel) {
        this.jumpModel = jumpModel;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }



}
