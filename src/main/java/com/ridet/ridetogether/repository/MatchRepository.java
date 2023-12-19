package com.ridet.ridetogether.repository;

import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.domain.User;

import java.util.Optional;

//TODO: MatchRepository 인터페이스 작성
public interface MatchRepository {

    public int open(Ride ride);
    public void close(int id);
    public Optional<Ride> findByUserId(int id);
    public int numOfCurrentRide();
}
