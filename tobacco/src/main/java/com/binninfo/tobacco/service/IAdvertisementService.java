package com.binninfo.tobacco.service;

import com.binninfo.tobacco.entity.Advertisement;
import com.binninfo.tobacco.entity.SaleInfo;

import java.util.List;

public interface IAdvertisementService {
    List<Advertisement> getAdvertisementInfo();
    boolean delAdvertisementInfo(String[] ids);
    boolean updateAdvertisementInfo(Advertisement Advertisement);
    boolean insertAdvertisementInfo(Advertisement Advertisement);
    boolean saveImg(String AdvertisementID,String imgURL);
}
