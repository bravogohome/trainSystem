package com.example.trainsystem.service.impl;

import com.example.trainsystem.entity.Trains;
import com.example.trainsystem.mapper.TrainsMapper;
import com.example.trainsystem.service.TrainsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TrainsServiceImpl extends ServiceImpl<TrainsMapper, Trains> implements TrainsService {
    @Autowired
    TrainsMapper trainsMapper;
    @Override
    public Map<String, Object> findTrainByTrainid(int trainid) {
        Map<String,Object> result=new HashMap<>();
        List<Trains> trainsList=trainsMapper.findTrainByTrainid(trainid);
        if (trainsList.size()>0){
            result.put("errorCode",0);
            result.put("message","查找成功");
            result.put("resultObject",trainsList.get(0));
        }else {
            result.put("errorCode",1);
            result.put("message","查无车次");
        }
        return result;
    }

    @Override
    public Map<String, Object> findTrainByTrainnum(String trainnum) {
        Map<String,Object> result=new HashMap<>();
        List<Trains> trainsList=trainsMapper.findTrainByTrainnum(trainnum);
        if (trainsList.size()>0){
            result.put("errorCode",0);
            result.put("message","查找成功");
            result.put("resultObject",trainsList.get(0));
        }else {
            result.put("errorCode",1);
            result.put("message","查无车次");
        }
        return result;
    }

    @Override
    public Map<String, Object> addTrain(String trainnum) {
        Map<String,Object> result=new HashMap<>();
        Trains newTrain=new Trains();
        newTrain.setTrainnum(trainnum);
        try{
            save(newTrain);
            result.put("errorCode",0);
            result.put("message","添加成功");
        }catch (Exception e){
            e.printStackTrace();
            result.put("errorCode",1);
            result.put("message","添加失败");
        }
        return result;
    }
}
