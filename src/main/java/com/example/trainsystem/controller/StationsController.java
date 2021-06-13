package com.example.trainsystem.controller;


import com.example.trainsystem.service.StationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/stations")
public class StationsController {

    @Autowired
    StationsService stationsService;

    @GetMapping("/addStation")
    public ModelAndView addStation(@RequestParam(value = "stationname")String stationname){
        stationname=stationname.replaceAll("\\s*", "");
        ModelAndView modelAndView=new ModelAndView();
        Map<String,Object> result=stationsService.addStation(stationname);
        switch((int)result.get("errorCode")){
            case 0:
                modelAndView.addObject("message",result.get("message"));
                modelAndView.setViewName("index");
                break;
            case 1:
                modelAndView.addObject("message",result.get("message"));
                modelAndView.setViewName("index");
                break;
            default:
        }
        return modelAndView;
    }

    @GetMapping("/findAllStation")
    public ModelAndView findAllStation(){
        ModelAndView modelAndView=new ModelAndView();
        Map<String,Object> result=stationsService.finaAllStation();
        modelAndView.addObject("message",result.get("message"));
        modelAndView.addObject("stationList",result.get("resultObject"));
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/findTrainOfStation")
    public ModelAndView findTrainOfStation(@RequestParam(value = "stationname")String stationname){
        stationname=stationname.replaceAll("\\s*", "");
        ModelAndView modelAndView=new ModelAndView();
        Map<String,Object> result=stationsService.findTrainOfStation(stationname);
        if ((int)result.get("errorCode")==0){
            modelAndView.addObject("trainnumList",result.get("resultObject"));
        }
        modelAndView.addObject("message",result.get("message"));
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
