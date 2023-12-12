package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.domain.Location;
import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.domain.User;
import com.ridet.ridetogether.domain.dto.MatchOpenDTO;
import com.ridet.ridetogether.repository.UserRepository;
import com.ridet.ridetogether.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class matchController {
    //TODO: UserService로 변경
    private final UserService userService;


    @Autowired
    public matchController(UserService userService) {
        this.userService = userService;
    }

    //TODO: api 접근 가능 도메인 제한 필요
    @InitBinder
    public void init() {
    }

    /**
     *
     * @param matchOpenDTO
     * @param session
     * @return
     * @throws Exception
     */
    @PostMapping("/open")
    public Map matchOpen(@RequestBody MatchOpenDTO matchOpenDTO,
                            HttpSession session) throws Exception {
        // 요청한 User 가져오기
        Long id = (Long) session.getAttribute("id");
        User user = userService.getUserById(id).orElseThrow();

        // DTO로부터 현재위치, 목적지 가져오기
        Location currentLocation = new Location(matchOpenDTO.getCurrentLatitude(), matchOpenDTO.getCurrentLongtitude());
        Location destinationLocation = new Location(matchOpenDTO.getDestinationLatitude(), matchOpenDTO.getDestinationLongtitude());

        // Ride 생성
        Ride ride = new Ride(user, currentLocation, destinationLocation, new Date(), null);

        //TODO: ride 매칭 시작부분 작성

        // json 형식 return
        Map result = new HashMap<String, Object>();
        result.put("isSuccess", "true");
        result.put("numOfRide", "0"); //TODO: Ride가 몇개 매칭중인지 리턴
        return result;
    }
}
