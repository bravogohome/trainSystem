package com.example.trainsystem.service.impl;

import com.example.trainsystem.entity.Stations;
import com.example.trainsystem.mapper.RoutesMapper;
import com.example.trainsystem.mapper.StationsMapper;
import com.example.trainsystem.mapper.TrainsMapper;
import com.example.trainsystem.service.StationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gohome
 * @since 2021-06-06
 */
@Service
public class StationsServiceImpl extends ServiceImpl<StationsMapper, Stations> implements StationsService {
    @Autowired
    StationsMapper stationsMapper;
    @Autowired
    RoutesMapper routesMapper;
    @Autowired
    TrainsMapper trainsMapper;

    @Transactional
    public Map<String, Object> searchTrain(int startStation, int endStation) {
        Map<String,Object> result=new HashMap<>();
        List<Stations> startStations=stationsMapper.selectStationByStationid(startStation);
        List<Stations> endStations=stationsMapper.selectStationByStationid(endStation);
        List<Integer> startStationTrainList1=new ArrayList<>();
        List<Integer> endStationTrainList1=new ArrayList<>();
        List<Integer> startStationTrainList2=new ArrayList<>();
        List<Integer> endStationTrainList2=new ArrayList<>();
        getTrainList(startStationTrainList1,startStations.get(0).getTraincode1());
        getTrainList(endStationTrainList1,endStations.get(0).getTraincode1());
        getTrainList(startStationTrainList2,startStations.get(0).getTraincode2());
        getTrainList(endStationTrainList2,endStations.get(0).getTraincode2());
        //并集
        for (int i=0;i<startStationTrainList2.size();i++){
            startStationTrainList1.add(startStationTrainList2.get(i)+30);
        }
        for (int i=0;i<endStationTrainList2.size();i++){
            endStationTrainList1.add(endStationTrainList2.get(i)+30);
        }
        //求交集
        List<Integer> trains = startStationTrainList1.stream()
                .filter(item -> endStationTrainList1.contains(item))
                .collect(toList());
        List<Integer> finalList=new ArrayList<>();
        //起点<终点  业务检查
        for (Integer trainId:trains){
            int startnum=routesMapper.selectRoutenumByTrainidAndStationid(trainId,startStation).get(0);
            int endnum=routesMapper.selectRoutenumByTrainidAndStationid(trainId,endStation).get(0);
            if (startnum<endnum){
                finalList.add(trainId);
            }
        }
        if(finalList.size()==0){
            result.put("errorCode",1);
            result.put("message","暂无列车");
        }else{
            result.put("errorCode",0);
            result.put("message","查找成功");
            result.put("resultObject",finalList);
        }
        return result;
    }

    private void getTrainList(List<Integer> trainList,int trainCode){
        if (trainCode!=0){
            int index=(int)(Math.log(trainCode)/Math.log(2));
            trainList.add(Integer.valueOf(index+1));
            trainCode-=(Math.pow(2,index));
            getTrainList(trainList,trainCode);
        }
    }

    @Override
    public Map<String, Object> addStation(String stationname) {
        Map<String,Object> result=new HashMap<>();
        Stations newStation=new Stations();
        newStation.setStationname(stationname);
        newStation.setTraincode1(0);
        newStation.setTraincode2(0);
        try{
            save(newStation);
            result.put("errorCode",0);
            result.put("message","添加成功");
        }catch (Exception e){
            e.printStackTrace();
            result.put("errorCode",1);
            result.put("message","添加失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> finaAllStation() {
        Map<String,Object> result=new HashMap<>();
        List<Stations> stationsList=stationsMapper.selectAllStations();
        result.put("errorCode",1);
        result.put("message","查找成功");
        result.put("resultObject",stationsList);
        return result;
    }

    @Override
    public Map<String, Object> findTrainOfStation(String stationname) {
        Map<String,Object> result=new HashMap<>();
        List<Stations> stations=stationsMapper.selectStationByStationname(stationname);
        if (stations.size()==0){
            result.put("errorCode",1);
            result.put("message","查找失败,无此列车");
            return result;
        }
        Stations station=stations.get(0);
        List<Integer> trainList1=new ArrayList<>();
        List<Integer> trainList2=new ArrayList<>();
        getTrainList(trainList1,station.getTraincode1());
        getTrainList(trainList2,station.getTraincode2());
        for (int i=0;i<trainList2.size();i++){
            trainList1.add(trainList2.get(i)+30);
        }
        List<String> trainList=new ArrayList<>();
        for (int i=0;i<trainList1.size();i++){
            trainList.add(trainsMapper.findTrainByTrainid(trainList1.get(i)).get(0).getTrainnum());
        }
        result.put("errorCode",0);
        result.put("message","查找成功");
        result.put("resultObject",trainList);
        return result;
    }
}