package com.example.trainsystem.service.impl;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.example.trainsystem.entity.Seatinformations;
import com.example.trainsystem.mapper.SeatinformationsMapper;
import com.example.trainsystem.service.SeatinformationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SeatinformationsServiceImpl extends ServiceImpl<SeatinformationsMapper, Seatinformations> implements SeatinformationsService {
    @Autowired
    SeatinformationsMapper seatinformationsMapper;

    @Transactional
    public Map<String, Object> getSeatInformations(int trainid, String traindate) {
        List<Seatinformations> seatinformations=seatinformationsMapper.selectSeatinformationByTrainidAndTraindate(trainid,traindate);
        Map<String,Object> result=new HashMap<>();
        result.put("errorCode",0);
        result.put("message","查找成功");
        result.put("resultObject",seatinformations);
        return result;
    }
}
