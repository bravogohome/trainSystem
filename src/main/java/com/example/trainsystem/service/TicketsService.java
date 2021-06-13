package com.example.trainsystem.service;

import com.example.trainsystem.entity.Tickets;
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
public interface TicketsService extends IService<Tickets> {
    Map<String,Object> listUserTickets(int userid);
}
