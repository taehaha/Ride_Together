package com.ridet.ridetogether.controller;

import com.ridet.ridetogether.dto.match.MatchInfoDto;
import com.ridet.ridetogether.dto.match.Match;
import com.ridet.ridetogether.dto.user.User;
import com.ridet.ridetogether.service.MatchService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api/match")
public class MatchController {
    MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("")
    public ResponseEntity<MatchInfoDto> matched(HttpSession session) {
        // 로그인 검증
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Match 검색
        Optional<Match> optionalMatch = matchService.findMatchByUserId(user.getId());

        ResponseEntity<MatchInfoDto> result;

        if (optionalMatch.isEmpty()) {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            MatchInfoDto matchInfoDto = new MatchInfoDto();
            Match match = optionalMatch.get();
            matchInfoDto.setDeparture(match.getDeparture());
            matchInfoDto.setDestination(match.getDestination());

            result = ResponseEntity.ok(matchInfoDto);
        }

        return result;
    }
}
