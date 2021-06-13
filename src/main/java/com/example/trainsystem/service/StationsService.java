package com.example.trainsystem.service;

import com.example.trainsystem.entity.Stations;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gohome
 * @since 2021-06-06
 */
public interface StationsService extends IService<Stations> {
    Map<String,Object> searchTrain(int startStation,int endStation);
    Map<String,Object> addStation(String stationname);
    Map<String,Object> finaAllStation();
    Map<String,Object> findTrainOfStation(String stationname);
}
