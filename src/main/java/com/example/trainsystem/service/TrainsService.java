package com.example.trainsystem.service;

import com.example.trainsystem.entity.Trains;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gohome
 * @since 2021-06-06
 */
public interface TrainsService extends IService<Trains> {
    Map<String,Object> findTrainByTrainid(int trainid);
    Map<String,Object> findTrainByTrainnum(String trainnum);
    Map<String,Object> addTrain(String trainnum);
}
