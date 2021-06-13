package com.example.trainsystem.controller;


import com.example.trainsystem.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/tickets")
public class TicketsController {
    @Autowired
    TicketsService ticketsService;

    @PostMapping("/listTicket")
    public ModelAndView listUserTickets(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String userid = parameterMap.get("userid")[0];
        Map<String,Object> tickets=ticketsService.listUserTickets(Integer.valueOf(userid));

        modelAndView.setViewName("");
        modelAndView.addObject("message",tickets.get("message"));
        return modelAndView;
    }
}
