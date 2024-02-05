package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.domain.Location;
import com.ridet.ridetogether.domain.Match;
import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.domain.dto.*;
import com.ridet.ridetogether.exception.RideAlreadyMatchedException;
import com.ridet.ridetogether.exception.RideAlreadyOpenedException;
import com.ridet.ridetogether.exception.RideNotFoundException;
import com.ridet.ridetogether.service.RideService;
import com.ridet.ridetogether.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/ride")
public class rideController {
    private final UserService userService;
    private final RideService rideService;

    @Autowired
    public rideController(UserService userService, RideService rideService) {
        this.userService = userService;
        this.rideService = rideService;
    }

    // Ride 생성 및 기존 Ride와 매칭 가능한지 확인
    @PostMapping("/open")
    public ResponseEntity<MatchOpenResponseDTO> matchOpen(@RequestBody MatchOpenDTO matchOpenDTO,
                                    HttpSession session) throws RideAlreadyOpenedException {
        // session에서 user id 가져오기
        Integer id = (Integer) session.getAttribute("id");

        // 로그인 여부 확인. 로그인이 안되어있으면 401을 반환함
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // DTO로부터 현재위치, 목적지 가져오기
        Location currentLocation = new Location(matchOpenDTO.getCurrentLatitude(), matchOpenDTO.getCurrentLongtitude());
        Location destinationLocation = new Location(matchOpenDTO.getDestinationLatitude(), matchOpenDTO.getDestinationLongtitude());

        // Ride 생성
        Ride ride = new Ride.Builder()
                .userId(id)
                .currentLocation(currentLocation)
                .destinationLocation(destinationLocation)
                .rideRequestDate(new Date())
                .matched(false)
                .build();

        MatchOpenResponseDTO returnDTO = new MatchOpenResponseDTO();

        // 매칭
        try {
            rideService.rideOpen(ride); //ride 생성
            rideService.matchLatestRide(); // 매칭 시작
            returnDTO.setSuccess(true);
        } catch (RideAlreadyOpenedException | RideAlreadyMatchedException e) {
            returnDTO.setSuccess(false);
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        returnDTO.setNumOfRide(rideService.numOfCurrentRide());

        return ResponseEntity.ok(returnDTO);
    }

    @PostMapping("/close")
    public ResponseEntity<Null> rideClose(HttpSession session) {
        // session에서 user id 가져오기
        Integer id = (Integer) session.getAttribute("id");

        // 로그인 여부 확인. 로그인이 안되어있으면 401을 반환함
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // 이미 매치가 생성되었을 경우 매치를 제거함
        Ride ride = rideService.findRideByUserId(id).orElse(null);

        if (ride != null && ride.isMatched()) {
            Match match = rideService.findMatchByUserId(id).orElse(null);
            rideService.matchClose(match.getId());
        }

        // 현재 로그인되어있는 user id로 생성된 ride를 제거함
        rideService.rideClose(ride.getId());

        return ResponseEntity.ok(null);
    }


    // 본인의 매칭 status 조회
    @PostMapping("/polling")
    public ResponseEntity<MatchInfoDTO> matchedPolling(HttpSession session) {
        // 로그인 검증
        Integer id = (Integer) session.getAttribute("id");
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        ResponseEntity<MatchInfoDTO> result;

        // 로그인된 user의 Ride가 존재하는지 확인
        Ride currentRide = rideService.findRideByUserId(id).orElse(null);
        // Match 검색
        Match currentMatch = rideService.findMatchByUserId(id).orElse(null);

        MatchInfoDTO returnDTO = new MatchInfoDTO();
        if (currentRide == null) {
            result = new ResponseEntity<>(HttpStatus.GONE);
            returnDTO.setStatus(HttpStatus.GONE);
        }
        else if (currentMatch == null) {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            returnDTO.setStatus(HttpStatus.NOT_FOUND);
        } else {
            returnDTO.setMatchId(currentMatch.getId());
            returnDTO.setUserId1(currentMatch.getUserId1());
            returnDTO.setUserId2(currentMatch.getUserId2());
            returnDTO.setDestination(currentMatch.getDeparture());
            returnDTO.setDeparture(currentMatch.getDestination());
            returnDTO.setMatchActivated(true);

            result = ResponseEntity.ok(returnDTO);
        }

        return result;
    }
}
