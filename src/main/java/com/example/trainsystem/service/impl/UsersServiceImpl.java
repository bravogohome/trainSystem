package com.example.trainsystem.service.impl;

import com.example.trainsystem.entity.Users;
import com.example.trainsystem.mapper.UsersMapper;
import com.example.trainsystem.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gohome
 * @since 2021-06-06
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
