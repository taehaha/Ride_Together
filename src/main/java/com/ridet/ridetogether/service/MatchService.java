package com.ridet.ridetogether.service;

import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.exception.RideAlreadyMatchedException;
import com.ridet.ridetogether.exception.RideAlreadyOpenedException;
import com.ridet.ridetogether.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public int matchOpen(Ride ride) throws RideAlreadyOpenedException, RideAlreadyMatchedException {
        // 이미 매칭이 완료된 Ride일 경우
        if (ride.getMatchedRide() != null) {
            throw new RideAlreadyMatchedException("이미 매칭이 된 Ride 입니다.");
        }

        // User가 이미 Ride를 open했을 경우
        if (matchRepository.findByUserId(ride.getUserId()).isPresent()) {
            throw new RideAlreadyOpenedException("이미 시작 된 Ride 가 있습니다.");
        }

        return matchRepository.open(ride);
    }

    public void matchClose(int id) {
        matchRepository.close(id);
    }

    public int numOfCurrentRide() {
        return matchRepository.numOfCurrentRide();
    }
}
