package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.domain.Location;
import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.domain.User;
import com.ridet.ridetogether.domain.dto.MatchOpenDTO;
import com.ridet.ridetogether.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public matchController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        User user;
        Long id = (Long) session.getAttribute("id");
        Optional<User> optionalUser = userRepository.getUserById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new Exception(); // 해당 id의 user가 존재하지 않을 경우 500을 날림
        }

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
