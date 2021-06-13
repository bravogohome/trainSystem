package com.example.trainsystem.mapper;

import com.example.trainsystem.TrainSystemApplication;
import com.example.trainsystem.entity.Users;
import com.example.trainsystem.service.StationsService;
import com.example.trainsystem.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TrainSystemApplication.class)
class UsersMapperTest {
    @Autowired
    StationsService service;

    @Resource
    UsersService usersService;

    @Autowired
    StationsMapper stationsMapper;

    @Test
    public void test1(){
        List<Users> userList = usersService.list();
        for (Users user : userList) {
            System.out.println(user);
        }
        service.searchTrain(stationsMapper.selectStationidByStationname("郑州").get(0),stationsMapper.selectStationidByStationname("许昌东").get(0));


    }

}