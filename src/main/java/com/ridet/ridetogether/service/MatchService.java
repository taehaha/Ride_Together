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


    //TODO: 매칭기능 메소드 구현 검증필요

    // 가장 최근에 추가된 Ride를 매칭
//    public void matchLatestRide() {
//        if (rideRepository.numOfCurrentRide() == 0) {
//            return; //TODO: RideRepositoryEmptyException 발생 필요
//        }
//
//        Ride[] currentRides = rideRepository.getRides();
//
//        // 매칭이 안된 가장 최근에 추가된 ride
//        Ride latestRide = currentRides[currentRides.length - 1];
//
//        // 각 Ride의 출발지와 도착지 간의 거리를 측정해 1km 이하인 ride를 찾는다.
//        Location latestCurr = latestRide.getCurrentLocation();
//        Location latestDest = latestRide.getDestinationLocation();
//
//        for (Ride candidateRide : currentRides) {
//            // 마지막 ride에 도달할 경우 매칭 탐색 종료
//            if (candidateRide == latestRide) break;
//
//            Location candidateCurr = candidateRide.getCurrentLocation();
//            Location candidateDest = candidateRide.getDestinationLocation();
//
//            double currDistance = getDistance(latestCurr, candidateCurr); // m단위
//            double destDistance = getDistance(latestDest, candidateDest); // m단위
//
//            // 매칭이 가능할 경우
//            if (currDistance <= 1000 && destDistance <= 1000) {
//                //TODO: 출발지, 도착지 위치선정 알고리즘 필요
//
//                // 매치 출발지, 도착지 생성
//                double departueLatitude = (latestCurr.getLatitude() + candidateCurr.getLatitude()) / 2;
//                double departureLongtitude = (latestCurr.getLongitude() + candidateCurr.getLongitude()) / 2;
//
//                Location departure = new Location.Builder()
//                        .latitude(departueLatitude)
//                        .longitude(departureLongtitude)
//                        .build();
//                Location destination = new Location.Builder()
//                        .latitude(departueLatitude)
//                        .longitude(departureLongtitude)
//                        .build();
//
//                // 매치 생성
//                Match newMatch = new Match.Builder()
//                        .userId1(latestRide.getUserId())
//                        .userId2(candidateRide.getUserId())
//                        .rideId1(latestRide.getId())
//                        .rideId2(candidateRide.getId())
//                        .departure(departure)
//                        .destination(destination)
//                        .date(new Date())
//                        .build();
//
//                // 매칭된 Ride들을 매칭완료 상태로 만듦
//                rideRepository.setMatched(candidateRide.getId(), latestRide.getId());
//                System.out.println("RideService.matchLatestRide: Match created.");
//                // 생성된 매치 저장
//                matchRepository.add(newMatch);
//                break;
//            }
//        }
//    }

    public Optional<Match> findMatchByUserId(int userId) {
        return Optional.ofNullable(matchMapper.findMatchByUserId(userId));
    }
}
