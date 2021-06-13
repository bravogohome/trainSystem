package com.example.trainsystem.service.impl;

import com.example.trainsystem.entity.Routes;
import com.example.trainsystem.entity.Stations;
import com.example.trainsystem.entity.Trains;
import com.example.trainsystem.mapper.RoutesMapper;
import com.example.trainsystem.mapper.StationsMapper;
import com.example.trainsystem.mapper.TrainsMapper;
import com.example.trainsystem.service.RoutesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gohome
 * @since 2021-06-06
 */
@Service
public class RoutesServiceImpl extends ServiceImpl<RoutesMapper, Routes> implements RoutesService {
    @Autowired
    RoutesMapper routesMapper;
    @Autowired
    TrainsMapper trainsMapper;
    @Autowired
    StationsMapper stationsMapper;

    @Override
    public Map<String, Object> findRouteByTrainnum(String trainnum) {
        Map<String,Object> result=new HashMap<>();
        List<Trains> trainsList =trainsMapper.findTrainByTrainnum(trainnum);
        if (trainsList.size()==0){
            result.put("errorCode",1);
            result.put("message","查找失败");
        }else{
            List<Routes> routesList=routesMapper.selectRouteByTrainid(trainsList.get(0).getTrainid());
            result.put("errorCode",0);
            result.put("message","查找成功");
            result.put("resultObject",routesList);
        }
        return result;
    }

    @Override
    public Map<String, Object> createRoute(int trainid, String stationname, String stoptime, int staytime) {
        Map<String,Object> result=new HashMap<>();
        try{
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime stopTime = LocalTime.parse(stoptime, fmt);
        }catch (Exception e){
            e.printStackTrace();
            result.put("errorCode",3);
            result.put("message","添加失败,时间格式错误");
            return result;
        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime stopTime = LocalTime.parse(stoptime, fmt);
        Routes newRoute=new Routes();
        List<Integer> last=routesMapper.findlast(trainid);
        int lastnum=0;
        if (last.size()>0){
            lastnum=last.get(0);
        }
        List<Stations> stations=stationsMapper.selectStationByStationname(stationname);
        if (routesMapper.selectRoutenumByTrainidAndStationid(trainid,stations.get(0).getStationid()).size()!=0){
            result.put("errorCode",2);
            result.put("message","添加失败,已经过该车站");
            return result;
        }
        Stations station=stations.get(0);
        newRoute.setRoutenum(lastnum+1);
        newRoute.setStoptime(stopTime);
        newRoute.setStaytime(staytime);
        newRoute.setTrainid(trainid);
        LocalTime startTime=stopTime.plusMinutes(staytime);
        newRoute.setStarttime(startTime);
        newRoute.setStaystationid(station.getStationid());
        try{
            save(newRoute);
            result.put("errorCode",0);
            result.put("message","添加成功");
            int traincode1=station.getTraincode1();
            int traincode2=station.getTraincode2();
            if (trainid>30){
                traincode2+=(Math.pow(2,trainid-31));
            }else{
                traincode1+=(Math.pow(2,trainid-1));
            }
            stationsMapper.updateTraincode(traincode1,traincode2,station.getStationid());
        }catch (Exception e){
            e.printStackTrace();
            result.put("errorCode",1);
            result.put("message","添加失败");
        }
        return result;
    }
}
