package com.ridet.ridetogether.repository;

import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.exception.RideNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryRideRepository implements RideRepository {
    private static Map<Integer, Ride> store = new HashMap<>();


    @Override
    public int open(Ride ride) {
        int ride_id = store.size();

        store.put(ride_id, ride);
        ride.setId(ride_id);

        return ride_id;
    }

    @Override
    public void close(int rideId) {
        store.remove(rideId);
    }

    //TODO: ATOMIC하게 실행되어야 함
    @Override
    public void setMatched(int rideId1, int rideId2) throws RideNotFoundException {
        Ride ride1 = this.findRideById(rideId1).orElse(null);
        Ride ride2 = this.findRideById(rideId2).orElse(null);

        if (ride1 == null || ride2 == null) {
            throw new RideNotFoundException("Ride가 존재하지 않습니다.");
        }

        // 해당 Ride 매칭완료 설정
        ride1.setMatched(true);
        ride2.setMatched(true);

        store.replace(rideId1, ride1);
        store.replace(rideId2, ride2);
    }

    @Override
    public void updateRide(Ride ride) {
        store.replace(ride.getId(), ride);
    }
    @Override
    public Ride[] getRides() {
        Ride[] returns = new Ride[numOfCurrentRide()];

        for (Ride rideTemp : store.values()) {
            returns[rideTemp.getId()] = rideTemp;
        }
        return  returns;
    }
    @Override
    public Optional<Ride> findRideById(int id) {
        Ride ride =  store.get(id);

        Optional<Ride> optionalRide;
        if (ride == null) {
            optionalRide = Optional.empty();
        } else {
            optionalRide = Optional.of(ride);
        }
        return optionalRide;
    }
    @Override
    public Optional<Ride> findByUserId(int id) {
        Ride ride = null;
        for (Ride tempRide : store.values()) {
            if (tempRide.getUserId() == id) {
                ride = tempRide;
            }
        }

        Optional<Ride> optionalRide;
        if (ride == null) {
            optionalRide = Optional.empty();
        } else {
            optionalRide = Optional.of(ride);
        }
        return optionalRide;
    }

    @Override
    public int numOfCurrentRide() {
        return store.size();
    }
}
