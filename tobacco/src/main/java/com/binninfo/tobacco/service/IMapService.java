package com.binninfo.tobacco.service;

import com.binninfo.tobacco.entity.Map;
import com.binninfo.tobacco.entity.SaleInfo;

import java.util.List;

public interface IMapService {
    List<Map> getMapInfo();
    boolean delMapInfo(String[] ids);
    boolean updateMapInfo(Map Map);
    boolean insertMapInfo(Map Map);
    boolean saveImg(String MapID,String imgURL);
}
