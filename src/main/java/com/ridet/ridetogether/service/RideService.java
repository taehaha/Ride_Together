package com.ridet.ridetogether.service;

import com.ridet.ridetogether.domain.Location;
import com.ridet.ridetogether.dto.match.Match;
import com.ridet.ridetogether.dto.ride.Ride;
import com.ridet.ridetogether.exception.RideOpenException;
import com.ridet.ridetogether.exception.RideNotFoundException;
import com.ridet.ridetogether.model.mapper.MatchMapper;
import com.ridet.ridetogether.model.mapper.RideMapper;
import com.ridet.ridetogether.util.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * <h2>Ride 등록 및 match관련 서비스</h2>
 */
@Service
public class RideService {
    private final RideMapper rideMapper;
    private final MatchMapper matchMapper;

    @Autowired
    public RideService(RideMapper rideMapper, MatchMapper matchMapper) {
        this.rideMapper = rideMapper;
        this.matchMapper = matchMapper;
    }


    public int open(Ride ride) throws RideOpenException {
        ride.setRequestDate(new Timestamp(System.currentTimeMillis()));

        int result = rideMapper.open(ride);

        if (result == 0) {
            throw new RideOpenException("이미 open된 Ride 또는 이미 Ride를 open한 user 입니다.");
        }

        return ride.getId();
    }


    public void close(int id) throws RideNotFoundException {
        int result = rideMapper.close(id);
        if (result == 0) {
            throw new RideNotFoundException("ride가 존재하지 않습니다.");
        }
    }

    public Optional<Ride> findRideById(int rideId) {
        return Optional.ofNullable(rideMapper.findRideById(rideId));
    }

    public Optional<Ride> findRideByUserId(int userId) {
        return Optional.ofNullable(rideMapper.findRideByUserId(userId));
    }
    public Optional<Match> match(Ride ride) {
        List<Ride> list = rideMapper.selectAll();

        Match newMatch = null;
        for (Ride candidateRide : list) {
            if (candidateRide.getId() == ride.getId()) continue;

            Location candidateLoc = candidateRide.getCurrentLocation();
            Location candidateDestLoc = candidateRide.getDestinationLocation();
            Location myLoc = ride.getCurrentLocation();
            Location myDestLoc = ride.getDestinationLocation();

            double distance = DistanceUtil.getDistance(candidateLoc, myLoc);
            double destDistance = DistanceUtil.getDistance(candidateDestLoc, myDestLoc);

            if (distance <= 500 && destDistance < 500) {
                newMatch = new Match.Builder()
                        .rideId1(ride.getId())
                        .rideId2(candidateRide.getId())
                        .departure(Location.middle(myLoc, candidateLoc))
                        .destinatioin(Location.middle(myDestLoc, candidateDestLoc))
                        .build();
                matchMapper.add(newMatch);
            }
        }

        return Optional.ofNullable(newMatch);
    }

    public int numOfCurrentRide() {
        return rideMapper.numOfCurrentRide();
    }
}
