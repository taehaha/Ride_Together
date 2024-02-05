package com.ridet.ridetogether.service;

import com.ridet.ridetogether.domain.Location;
import com.ridet.ridetogether.domain.Match;
import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.exception.MatchNotFoundException;
import com.ridet.ridetogether.exception.RideAlreadyMatchedException;
import com.ridet.ridetogether.exception.RideAlreadyOpenedException;
import com.ridet.ridetogether.exception.RideNotFoundException;
import com.ridet.ridetogether.repository.MatchRepository;
import com.ridet.ridetogether.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * <h2>Ride 등록 및 match관련 서비스</h2>
 */
@Service
public class RideService {
    private final RideRepository rideRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public RideService(RideRepository rideRepository, MatchRepository matchRepository) {
        this.rideRepository = rideRepository;
        this.matchRepository = matchRepository;
    }

    public int rideOpen(Ride ride) throws RideAlreadyOpenedException, RideAlreadyMatchedException {
        // 이미 매칭이 완료된 Ride일 경우
        if (ride.isMatched()) {
            throw new RideAlreadyMatchedException("이미 매칭이 된 Ride 입니다. user id = " + ride.getUserId());
        }

        // User가 이미 Ride를 open했을 경우
        if (rideRepository.findByUserId(ride.getUserId()).isPresent()) {
            throw new RideAlreadyOpenedException("이미 시작 된 Ride 가 있습니다. user id = " + ride.getUserId());
        }

        return rideRepository.open(ride);
    }

    public void rideClose(int id) throws RideNotFoundException {
        Ride ride = rideRepository.findRideById(id).orElse(null);
        if (ride == null) {
            throw new RideNotFoundException("ride가 존재하지 않습니다.");
        }
        rideRepository.close(id);
    }

    public void matchClose(String id) throws MatchNotFoundException {
        Match match = matchRepository.findByMatchId(id).orElse(null);

        // 해당하는 Match가 없을 경우 MatchNotFoundException 발생
        if (match == null) {
            throw new MatchNotFoundException("Match를 찾을 수 없습니다.");
        }

        // Match에 연관된 Ride의 matched를 false로 설정
        Ride ride1 = rideRepository.findRideById(match.getRideId1()).orElse(null);
        Ride ride2 = rideRepository.findRideById(match.getRideId2()).orElse(null);

        ride1.setMatched(false);
        ride2.setMatched(false);

        rideRepository.updateRide(ride1);
        rideRepository.updateRide(ride2);

        matchRepository.remove(id);
    }

    public Optional<Ride> findRideByUserId(int id) {
        return rideRepository.findByUserId(id);
    }


    //TODO: 매칭기능 메소드 구현 검증필요

    // 가장 최근에 추가된 Ride를 매칭
    public void matchLatestRide() {
        Ride[] matchingRides = rideRepository.getRides();

        if (matchingRides.length == 0) {
            return; //TODO: RideRepositoryEmptyException 발생 필요
        }

        // 매칭이 안된 가장 최근에 추가된 ride
        Ride latestRide = null;
        for (int index = matchingRides.length - 1; index >= 0; index--) {
            if (!matchingRides[index].isMatched()) {
                latestRide = matchingRides[index];
                break;
            }
        }
        if (latestRide == null) {
            //TODO: NoneOfAvailableRideException 발생
        }

        // 각 Ride의 출발지와 도착지 간의 거리를 측정해 1km 이하인 ride를 찾는다.
        Location latestCurr = latestRide.getCurrentLocation();
        Location latestDest = latestRide.getDestinationLocation();

        for (int index = 0; index < matchingRides.length - 1; index++) {
            Ride candidateRide = matchingRides[index];

            Location candidateCurr = candidateRide.getCurrentLocation();
            Location candidateDest = candidateRide.getDestinationLocation();

            double currDistance = getDistance(latestCurr, candidateCurr); // m단위
            double destDistance = getDistance(latestDest, candidateDest); // m단위

            // 매칭이 가능할 경우
            if (currDistance <= 1000 && destDistance <= 1000) {
                //TODO: 출발지, 도착지 위치선정 알고리즘 필요

                // 매치 출발지, 도착지 생성
                Location departure = new Location((latestCurr.getLatitude() + candidateCurr.getLatitude()) / 2,
                        (latestCurr.getLongtitude() + candidateCurr.getLongtitude()) / 2);

                Location destination = new Location((latestDest.getLatitude() + candidateDest.getLatitude()) / 2,
                        (latestDest.getLongtitude() + candidateDest.getLongtitude()) / 2);

                // 매치 생성
                Match newMatch = new Match.Builder()
                        .userId1(latestRide.getUserId())
                        .userId2(candidateRide.getUserId())
                        .rideId1(latestRide.getId())
                        .rideId2(candidateRide.getId())
                        .departure(departure)
                        .destination(destination)
                        .date(new Date())
                        .build();

                // 매칭된 Ride들을 매칭완료 상태로 만듦
                rideRepository.setMatched(candidateRide.getId(), latestRide.getId());
                System.out.println("RideService.matchLatestRide: Match created.");
                // 생성된 매치 저장
                matchRepository.add(newMatch);
                break;
            }
        }
    }

    public Optional<Match> findMatchByUserId(int userId) {
        return matchRepository.findByUserId(userId);
    }

    public int numOfCurrentRide() {
        return rideRepository.numOfCurrentRide();
    }


    //TODO: 분리 필요
    private static double getDistance(Location loc1, Location loc2) {
        double latitudeDistance = Math.toRadians(Math.abs(loc1.getLatitude() - loc2.getLatitude()));
        double longtitudeDistance = Math.toRadians(Math.abs(loc1.getLongtitude() - loc2.getLongtitude()));

        double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2)
                + Math.cos(Math.toRadians(loc1.getLatitude())) * Math.cos(Math.toRadians(loc2.getLatitude()))
                * Math.sin(longtitudeDistance / 2) * Math.sin(longtitudeDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c * 1000; // 거리 m 단위
    }
}
