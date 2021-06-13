package com.example.trainsystem.service;

import com.example.trainsystem.entity.Seatinformations;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gohome
 * @since 2021-06-06
 */
public interface SeatinformationsService extends IService<Seatinformations> {
    Map<String,Object> getSeatInformations(int trainid,String traindate);
}
