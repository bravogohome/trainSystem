package com.example.trainsystem.service;

import com.example.trainsystem.entity.Routes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gohome
 * @since 2021-06-06
 */
public interface RoutesService extends IService<Routes> {
    Map<String,Object> findRouteByTrainnum(String trainnum);

    Map<String,Object> createRoute(int trainid, String stationname, String stoptime,int staytime);
}
