package com.ridet.ridetogether.service;

import com.ridet.ridetogether.domain.Location;
import com.ridet.ridetogether.dto.match.Match;
import com.ridet.ridetogether.dto.ride.Ride;
import com.ridet.ridetogether.exception.MatchNotFoundException;
import com.ridet.ridetogether.model.mapper.MatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MatchService {
    MatchMapper matchMapper;

    @Autowired
    public MatchService(MatchMapper matchMapper) {
        this.matchMapper = matchMapper;
    }

    public void delete(int matchId) throws MatchNotFoundException {
        int result = matchMapper.delete(matchId);

        if (result == 0) {
            throw new MatchNotFoundException("Match를 찾을 수 없습니다.");
        }
    }

    public Optional<Match> findMatchByUserId(int userId) {
        return Optional.ofNullable(matchMapper.findMatchByUserId(userId));
    }
}
