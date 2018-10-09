package com.binninfo.tobacco.service;

import com.binninfo.tobacco.entity.Map;
import com.binninfo.tobacco.mapper.MapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService implements IMapService {

    @Autowired
    MapMapper mapMapper;
    @Override
    public List<Map> getMapInfo() {
        return mapMapper.getMaps();
    }

    @Override
    public boolean delMapInfo(String[] ids) {
        for(String id:ids){
            mapMapper.delMapInfo(id);
        }
        return true;
    }

    @Override
    public boolean updateMapInfo(Map map) {

        return mapMapper.updateMapInfo(map);
    }

    @Override
    public boolean insertMapInfo(Map map) {
        return mapMapper.saveMapInfo(map);
    }


    @Override
    public boolean saveImg(String id, String imgURL) {
        return mapMapper.saveImg(id,imgURL);
    }
}
