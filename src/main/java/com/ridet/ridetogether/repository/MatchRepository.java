package com.ridet.ridetogether.repository;

import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.domain.User;

import java.util.Optional;

//TODO: MatchRepository 인터페이스 작성
public interface MatchRepository {

    public void open(Ride ride);
    public void close(Ride ride1, Ride ride2);
    public Optional<Ride> findByUser(User user);
}
