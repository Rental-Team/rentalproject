package com.rentalproject.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentalproject.dto.VisitDto;
import com.rentalproject.service.VisitService;

@Controller
@RequestMapping("/visit")
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }
    
    @GetMapping("/")
    public String home(Model model) {
        int visitCount = visitService.getVisitCountToday();
        List<VisitDto> weeklyData = visitService.getWeeklyVisitData();

        model.addAttribute("visitCount", visitCount);
        model.addAttribute("weeklyData", weeklyData);
        
        return "home"; // 뷰 이름을 반환하도록 변경
    }

    @GetMapping("/weekly")
    public String getWeeklyVisitData(Model model) {
        List<VisitDto> weeklyData = visitService.getWeeklyVisitData();
        model.addAttribute("weeklyData", weeklyData);
        return "weeklyVisit"; // 뷰 이름을 반환하도록 변경
    }

    @GetMapping("/data")
    public VisitDto getVisitDataByDate(@RequestParam("date") String dateString) {
        Date date = Date.valueOf(dateString);
        return visitService.getVisitDataByDate(date);
    }

    @GetMapping("/add")
    public String addVisit(@RequestParam("date") String dateString) {
        Date date = Date.valueOf(dateString);
        visitService.addVisit(date);
        return "Visit added for date: " + dateString;
    }
}