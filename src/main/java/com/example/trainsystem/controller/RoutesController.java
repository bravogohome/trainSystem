package com.example.trainsystem.controller;


import com.example.trainsystem.entity.Routes;
import com.example.trainsystem.entity.Stations;
import com.example.trainsystem.entity.Trains;
import com.example.trainsystem.mapper.StationsMapper;
import com.example.trainsystem.mapper.TrainsMapper;
import com.example.trainsystem.service.RoutesService;
import com.example.trainsystem.service.StationsService;
import com.example.trainsystem.service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/routes")
public class RoutesController {
    @Autowired
    RoutesService routesService;
    @Autowired
    StationsMapper stationsMapper;
    @Autowired
    TrainsMapper trainsMapper;
    @Autowired
    StationsService stationsService;
    @Autowired
    TrainsService trainsService;


    @GetMapping("/findRouteByTrainnum")
    public ModelAndView findRouteByTrainnum(@RequestParam(value = "trainnum")String trainnum){
        trainnum=trainnum.replaceAll("\\s*", "");
        trainnum=trainnum.toUpperCase();
        ModelAndView modelAndView=new ModelAndView();
        Map<String,Object> result=routesService.findRouteByTrainnum(trainnum);
        if ((int)result.get("errorCode")==0){
            List<Routes> routesList=(List<Routes>)result.get("resultObject");
            modelAndView.addObject("routeList",routesList);
            List<String> stationList=new ArrayList<>();
            for (int i=0;i<routesList.size();i++){
                stationList.add(stationsMapper.selectStationByStationid(routesList.get(i).getStaystationid()).get(0).getStationname());
            }
            modelAndView.addObject("stationlist",stationList);
        }
        modelAndView.addObject("trainnum",trainnum);
        modelAndView.addObject("message",result.get("message"));
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/createRoute")
    public ModelAndView createRoute(@RequestParam(value = "trainnum")String trainnum,@RequestParam(value = "stoptime")String stoptime,
                                    @RequestParam(value = "staytime")int staytime,@RequestParam(value = "stationname")String stationname){
        stationname=stationname.replaceAll("\\s*", "");
        stoptime=stoptime.replaceAll("\\s*", "");
        trainnum=trainnum.replaceAll("\\s*", "");
        trainnum=trainnum.toUpperCase();
        ModelAndView modelAndView=new ModelAndView();
        Map<String,Object> result=new HashMap<>();
        List<Integer> findStation=stationsMapper.selectStationidByStationname(stationname);
        List<Trains> findTrain=trainsMapper.findTrainByTrainnum(trainnum);
        if (findStation.size()==0){
            stationsService.addStation(stationname);
        }
        if (findTrain.size()==0){
            trainsService.addTrain(trainnum);
        }
        int trainid=(int)trainsMapper.findTrainByTrainnum(trainnum).get(0).getTrainid();
        //int stationid=(int)stationsMapper.selectStationidByStationname(stationname).get(0);
        result=routesService.createRoute(trainid,stationname,stoptime,staytime);
        modelAndView.addObject("message",result.get("message"));
        modelAndView.addObject("trainnum",trainnum);
        modelAndView.setViewName("redirect:/routes/findRouteByTrainnum");
        return modelAndView;
    }
}
