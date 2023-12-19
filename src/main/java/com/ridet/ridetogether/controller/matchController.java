package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.domain.Location;
import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.domain.User;
import com.ridet.ridetogether.domain.dto.MatchOpenDTO;
import com.ridet.ridetogether.exception.RideAlreadyMatchedException;
import com.ridet.ridetogether.exception.RideAlreadyOpenedException;
import com.ridet.ridetogether.service.MatchService;
import com.ridet.ridetogether.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class matchController {
    private final UserService userService;
    private final MatchService matchService;

    @Autowired
    public matchController(UserService userService, MatchService matchService) {
        this.userService = userService;
        this.matchService = matchService;
    }


    //TODO: api 접근 가능 도메인 제한 필요
    @InitBinder
    public void init() {
    }

    @PostMapping("/open")
    public Map matchOpen(@RequestBody MatchOpenDTO matchOpenDTO,
                            HttpSession session) throws RideAlreadyOpenedException {
        // 요청한 User 가져오기
        int id = (int) session.getAttribute("id");
        User user = userService.getUserById(id).orElseThrow();

        // DTO로부터 현재위치, 목적지 가져오기
        Location currentLocation = new Location(matchOpenDTO.getCurrentLatitude(), matchOpenDTO.getCurrentLongtitude());
        Location destinationLocation = new Location(matchOpenDTO.getDestinationLatitude(), matchOpenDTO.getDestinationLongtitude());

        // Ride 생성
        Ride ride = new Ride(user.getId(), currentLocation, destinationLocation, new Date(), null);

        // json 형식 return값
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", "true");
        result.put("message", null);
        result.put("numOfRide", matchService.numOfCurrentRide());

        // 매칭
        try {
            matchService.matchOpen(ride);
        } catch (RideAlreadyOpenedException | RideAlreadyMatchedException e) {
            result.replace("isSuccess", "false");
            result.replace("message", e.getMessage());
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return result;
    }
}
