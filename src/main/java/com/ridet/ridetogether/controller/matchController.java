package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.domain.Location;
import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.domain.User;
import com.ridet.ridetogether.domain.dto.MatchOpenDTO;
import com.ridet.ridetogether.domain.dto.MatchOpenResponseDTO;
import com.ridet.ridetogether.exception.RideAlreadyMatchedException;
import com.ridet.ridetogether.exception.RideAlreadyOpenedException;
import com.ridet.ridetogether.service.MatchService;
import com.ridet.ridetogether.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping("/open")
    public ResponseEntity<MatchOpenResponseDTO> matchOpen(@RequestBody MatchOpenDTO matchOpenDTO,
                                    HttpSession session) throws RideAlreadyOpenedException {
        // session에서 user id 가져오기
        Integer id = (Integer) session.getAttribute("id");

        // 로그인 여부 확인. 로그인이 안되어있으면 401을 반환함
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // API요청한 User 가져오기
        User user = userService.getUserById(id).orElseThrow();

        // DTO로부터 현재위치, 목적지 가져오기
        Location currentLocation = new Location(matchOpenDTO.getCurrentLatitude(), matchOpenDTO.getCurrentLongtitude());
        Location destinationLocation = new Location(matchOpenDTO.getDestinationLatitude(), matchOpenDTO.getDestinationLongtitude());

        // Ride 생성
        Ride ride = new Ride(matchService.numOfCurrentRide(), user.getId(), currentLocation, destinationLocation, new Date(), null);

        MatchOpenResponseDTO returnDTO = new MatchOpenResponseDTO();

        // 매칭
        try {
            matchService.matchOpen(ride);
            returnDTO.setSuccess(true);
        } catch (RideAlreadyOpenedException | RideAlreadyMatchedException e) {
            returnDTO.setSuccess(false);
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        returnDTO.setNumOfRide(matchService.numOfCurrentRide());

        return ResponseEntity.ok(returnDTO);
    }
}
