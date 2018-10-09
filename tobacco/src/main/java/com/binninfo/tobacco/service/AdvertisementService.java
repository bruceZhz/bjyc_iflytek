package com.binninfo.tobacco.service;

import com.binninfo.tobacco.entity.Advertisement;
import com.binninfo.tobacco.mapper.AdvertisementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService implements IAdvertisementService{
    @Autowired
    AdvertisementMapper advertisementMapper;
    @Override
    public List<Advertisement> getAdvertisementInfo() {
        return advertisementMapper.getAdvertisementInfo();
    }

    @Override
    public boolean delAdvertisementInfo(String[] ids) {
        for(int i=0 ; i <ids.length ; i++){
            advertisementMapper.delAdvertisementInfo(ids[0]);
        }
        return true;
    }

    @Override
    public boolean updateAdvertisementInfo(Advertisement advertisement) {
        return advertisementMapper.updateAdvertisementInfo(advertisement);
    }

    @Override
    public boolean insertAdvertisementInfo(Advertisement advertisement) {
        return advertisementMapper.insertAdvertisementInfo(advertisement);
    }

    @Override
    public boolean saveImg(String id, String vidoURL) {
        return advertisementMapper.saveImg(id,vidoURL);
    }
}
