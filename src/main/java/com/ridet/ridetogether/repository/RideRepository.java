package com.ridet.ridetogether.repository;

import com.ridet.ridetogether.domain.Ride;

import java.util.Optional;

public interface RideRepository {

    public int open(Ride ride);
    public void close(int rideId);
    public void updateRide(Ride ride);
    public void setMatched(int rideId1, int rideId2);

    public Ride[] getRides();
    public Optional<Ride> findRideById(int id);
    public Optional<Ride> findByUserId(int id);
    public int numOfCurrentRide();
}
