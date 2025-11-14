package com.app.backend.controller;

import com.app.backend.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController{
    @Autowired
    private StatsService statsService;

    @GetMapping("")
    public Map<String, Long> getStats(){
        return statsService.getStats();
    }
}