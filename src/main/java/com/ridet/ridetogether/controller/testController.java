package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.dto.match.Match;
import com.ridet.ridetogether.dto.ride.Ride;
import com.ridet.ridetogether.model.mapper.MatchMapper;
import com.ridet.ridetogether.model.mapper.RideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {
    MatchMapper matchMapper;
    RideMapper rideMapper;

    @Autowired
    public testController(MatchMapper matchMapper, RideMapper rideMapper) {
        this.matchMapper = matchMapper;
        this.rideMapper = rideMapper;
    }

    @GetMapping("/test")
    public String t() {
        for (Ride r : rideMapper.selectAll()) {
            System.out.println(r);
        }
        return "index";
    }
}