package com.ridet.ridetogether.domain.dao;

import com.ridet.ridetogether.domain.Match;

import java.util.Optional;

/**
 * <h2>매칭이 완료된 Ride들의 정보를 관리하는 Repository</h2>
 */
public interface MatchRepository {
    public String add(Match match);
    public void remove(String id);
    public Optional<Match> findByMatchId(String id);
    public Optional<Match> findByUserId(int id);
    public Optional<Match> findByRideId(int id);
    public int numOfCurrentMatch();
}
