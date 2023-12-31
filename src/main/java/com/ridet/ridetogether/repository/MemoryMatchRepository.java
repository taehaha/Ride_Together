package com.ridet.ridetogether.repository;

import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryMatchRepository implements MatchRepository {
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

    @Override
    public Optional<Ride> findByUserId(int id) {
        Ride ride = store.get(id);

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
