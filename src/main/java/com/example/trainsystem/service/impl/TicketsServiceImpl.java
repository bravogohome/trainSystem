package com.example.trainsystem.service.impl;

import com.example.trainsystem.entity.Tickets;
import com.example.trainsystem.mapper.TicketsMapper;
import com.example.trainsystem.service.TicketsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
public class TicketsServiceImpl extends ServiceImpl<TicketsMapper, Tickets> implements TicketsService {
    @Autowired
    TicketsMapper ticketsMapper;

    @Transactional
    public Map<String, Object> listUserTickets(int userid) {
        Map<String,Object> result=new HashMap<>();
        List<Tickets> tickets=ticketsMapper.selectTicketByUserid(Integer.valueOf(userid));
        if (tickets.size()>0){
            result.put("errorCode",0);
            result.put("message","查找成功");
            result.put("resultObject",tickets);
        }else{
            result.put("errorCode",1);
            result.put("message","暂无车票");
        }
        return result;
    }
}
