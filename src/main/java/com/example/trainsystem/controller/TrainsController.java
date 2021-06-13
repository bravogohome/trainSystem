package com.example.trainsystem.controller;


import com.example.trainsystem.entity.Trains;
import com.example.trainsystem.mapper.SeatinformationsMapper;
import com.example.trainsystem.mapper.StationsMapper;
import com.example.trainsystem.mapper.TrainsMapper;
import com.example.trainsystem.service.SeatinformationsService;
import com.example.trainsystem.service.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gohome
 * @since 2021-06-06
 */
@Controller
@RequestMapping("/trains")
public class TrainsController {
    @Autowired
    StationsMapper stationsMapper;
    @Autowired
    SeatinformationsService seatinformationsService;
    @Autowired
    StationsService stationsService;
    @Autowired
    TrainsMapper trainsMapper;

    @GetMapping("/searchTrains") //输入起点终点查询车次
    public ModelAndView searchTrains(@RequestParam(value = "startStation") String startStation,@RequestParam(value = "endStation") String endStation,@RequestParam(value="selectDate")String selectDate){
        startStation=startStation.replaceAll("\\s*", "");
        endStation=endStation.replaceAll("\\s*", "");
        ModelAndView modelAndView=new ModelAndView();
        Map<String,Object> searchResult=stationsService.searchTrain(stationsMapper.selectStationidByStationname(startStation).get(0),stationsMapper.selectStationidByStationname(endStation).get(0));
        switch((int)searchResult.get("errorCode")){
            case 0:
                List<Trains> trains=new ArrayList<>();
              //  List<Map<String,Object>> resultList=new ArrayList<>();
                List<Integer> trainList=(List<Integer>)searchResult.get("resultObject");
                for (int i=0;i<trainList.size();i++){
               //     resultList.add(seatinformationsService.getSeatInformations(trainList.get(i),selectDate));
                    trains.add(trainsMapper.findTrainByTrainid(trainList.get(i)).get(0));
                }
                modelAndView.addObject("trainList",trains);
                break;
            case 1:

                break;
            default:
        }
        modelAndView.setViewName("index");
        modelAndView.addObject("message", searchResult.get("message"));
        return modelAndView;
    }
}
