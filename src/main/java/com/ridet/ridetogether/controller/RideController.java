package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.domain.Location;
import com.ridet.ridetogether.dto.ride.Ride;
import com.ridet.ridetogether.dto.user.User;
import com.ridet.ridetogether.exception.RideAlreadyMatchedException;
import com.ridet.ridetogether.exception.RideOpenException;
import com.ridet.ridetogether.dto.ride.RideOpenDto;
import com.ridet.ridetogether.dto.ride.RideOpenResponseDto;
import com.ridet.ridetogether.service.MatchService;
import com.ridet.ridetogether.service.RideService;
import com.ridet.ridetogether.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/ride")
public class RideController {
    private final UserService userService;
    private final RideService rideService;
    private final MatchService matchService;

    @Autowired
    public RideController(UserService userService, RideService rideService, MatchService matchService) {
        this.userService = userService;
        this.rideService = rideService;
        this.matchService = matchService;
    }

    // Ride 생성 및 기존 Ride와 매칭 가능한지 확인
    @PostMapping("")
    public ResponseEntity<RideOpenResponseDto> rideOpen(@RequestBody RideOpenDto rideOpenDto, HttpSession session) throws RideOpenException {
        // session에서 user id 가져오기
        User user = (User) session.getAttribute("user");

        // 로그인 여부 확인. 로그인이 안되어있으면 401을 반환함
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // DTO로부터 현재위치, 목적지 가져오기
        Location currentLocation = new Location.Builder()
                                                .latitude(rideOpenDto.getCurrentLatitude())
                                                .longitude(rideOpenDto.getCurrentLongtitude())
                                                .build();

        Location destinationLocation = new Location.Builder()
                                                    .latitude(rideOpenDto.getCurrentLatitude())
                                                    .longitude(rideOpenDto.getCurrentLongtitude())
                                                    .build();

        // Ride 생성
        Ride ride = new Ride.Builder()
                .userId(user.getId())
                .currentLocation(currentLocation)
                .destinationLocation(destinationLocation)
                .build();

        RideOpenResponseDto responseDto = new RideOpenResponseDto();

        // 매칭
        try {
            rideService.open(ride); //ride 생성
            rideService.match(ride); // 매칭 시작
            responseDto.setSuccess(true);
        } catch (RideOpenException | RideAlreadyMatchedException e) {
            responseDto.setSuccess(false);
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        responseDto.setNumOfRide(rideService.numOfCurrentRide());

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("")
    public ResponseEntity<?> rideClose(HttpSession session) {
        // session에서 user id 가져오기
        User user = (User) session.getAttribute("user");

        // 로그인 여부 확인. 로그인이 안되어있으면 401을 반환함
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<Ride> optionalRide = rideService.findRideByUserId(user.getId());

        if (!optionalRide.isEmpty()) {
            Ride ride = optionalRide.get();
            if (ride.getUserId() == user.getId()) {
                rideService.close(ride.getId());
                return ResponseEntity.ok(null);
            }
        }

        return ResponseEntity.badRequest().build();
    }
}

