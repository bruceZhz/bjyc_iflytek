package com.binninfo.tobacco.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.binninfo.tobacco.entity.Advertisement;
import com.binninfo.tobacco.entity.Goods;
import com.binninfo.tobacco.entity.SaleInfo;
import com.binninfo.tobacco.entity.answerEntity.Action;
import com.binninfo.tobacco.entity.answerEntity.AnswerBean;
import com.binninfo.tobacco.entity.answerEntity.ListBean;
import com.binninfo.tobacco.mapper.AdvertisementMapper;
import com.binninfo.tobacco.mapper.GoodsInfoMapper;
import com.binninfo.tobacco.mapper.MapMapper;
import com.binninfo.tobacco.util.HttpClient4Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class NlpService implements INlpService{
    @Autowired
    GoodsInfoMapper goodsInfoMapper;

    @Autowired
    AdvertisementMapper advertisementMapper;

    @Autowired
    MapMapper mapMapper;

    @Override
    public AnswerBean getAnswer(String sourceTerminal, String content, String iss_uid, String type,String url) {
        AnswerBean answerBean = new AnswerBean();

        Map<String,String> map = new HashMap<>();
        map.put("sourceTerminal",sourceTerminal);
        map.put("content",content);
        map.put("iss_uid",iss_uid);
        map.put("type",type);
        String answer = HttpClient4Util.post(url,map);

        JSONObject jsonObject = JSONObject.parseObject(answer);
        if(jsonObject.getInteger("rc") ==0){
            answerBean.setRc(0);
            String anStr = jsonObject.getString("answer");
            anStr = anStr.replace("＂","\"");
            anStr = anStr.replace("，",",");
            JSONObject answerObject = JSONObject.parseObject(anStr);
            int t = answerObject.getInteger("type");
            if(answerObject.containsKey("action")){
                Action action = new Action();
                int actionType = answerObject.getInteger("actionType");
                switch (actionType){
                    case 1:
                        action.setAction(answerObject.getString("action"));
                        action.setType(answerObject.getString("actionType"));
                        answerBean.setAction(action);
                        break;
                    case 2:
                        com.binninfo.tobacco.entity.Map map1 = mapMapper.getMapInfoByID(answerObject.getString("action"));
                        action.setType(2+"");
                        action.setAddress(map1);
                        answerBean.setAction(action);
                }

            }
            if(answerObject.containsKey("isSleep")){
                boolean isSleep = answerObject.getBoolean("isSleep");
                answerBean.setIsSleep(isSleep);
            }
            if(t==1){
                answerBean.setType("1");
                answerBean.setAnswer(answerObject.getString("answer"));
            }else if(t==2){
                String goodsID = answerObject.getString("answer");
                Goods goods = goodsInfoMapper.getGoodsInfoByID(goodsID);
                if(null!=goods){
                    String lat = answerObject.getString("lat");
                    if("价格".equals(lat)){
                        answerBean.setAnswer("商品价格为"+goods.getPrice());
                    }else if("介绍".equals(lat)){
                        answerBean.setAnswer(goods.getDetail());
                    }
                    SaleInfo saleInfo = goodsInfoMapper.getSaleInfo(goods.getId());
                    goods.setPromotions(saleInfo);
                    answerBean.setGoods(goods);
                    answerBean.setType("3");
                }else{
                    answerBean.setRc(-1);
                }

            }else if(t==3){
                String id = answerObject.getString("answer");
                Advertisement advertisement = advertisementMapper.getAdById(id);
                if(null!=advertisement){
                    if(null!=advertisement.getVidoURL()&&!"".equals(advertisement.getVidoURL())){
                        answerBean.setVidoURL(advertisement.getVidoURL());
                        answerBean.setRc(0);
                        answerBean.setType(2+"");
                    }else{
                        answerBean.setRc(0);
                        answerBean.setAnswer(advertisement.getContent());
                        answerBean.setType(1+"");
                    }
                }else{
                    answerBean.setRc(-1);
                }
            }else if(t==4){
                String id = answerObject.getString("answer");
                if("宣传".equals(id)){
                    List<Advertisement> advertisements = advertisementMapper.getAdvertisementInfo();
                    List<ListBean> listBeans = new ArrayList<>();
                    for(Advertisement advertisement:advertisements){
                        ListBean listBean = new ListBean();
                        listBean.setTitle(advertisement.getTitle());
                        listBeans.add(listBean);
                    }
                    answerBean.setRc(0);
                    answerBean.setType("4");
                    answerBean.setList(listBeans);
                    answerBean.setAnswer("为您查询到以下活动");
                }if("商品".equals(id)){
                    if(answerObject.containsKey("searchParams")){
                        Goods goods = new Goods();
                        JSONArray jsonArray = answerObject.getJSONArray("searchParams");
                        for(int i=0 ; i<jsonArray.size() ; i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String param = object.getString("name");
                            String value = object.getString("value");
                            if("goodsType".equals(param)){
                                goods.setGoodsType(value);
                            }else if("address".equals(param)){
                                goods.setAddress(value);
                            }
                        }
                        List<Goods> goodsList = goodsInfoMapper.searchGoodsInfo(goods);
                        List<ListBean> listBeans = new ArrayList<>();
                        for(int i=0 ; i<goodsList.size() ; i++){
                            ListBean listBean= new ListBean();
                            listBean.setTitle(goodsList.get(i).getName());
                            listBeans.add(listBean);
                        }
                        answerBean.setRc(0);
                        answerBean.setType("4");
                        answerBean.setList(listBeans);
                        answerBean.setAnswer("为您查询到以下商品");
                    }
                }

            }
        }else {
            answerBean.setRc(-1);
        }
        return answerBean;
    }
}
