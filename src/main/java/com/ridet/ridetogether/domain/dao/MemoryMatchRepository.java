package com.ridet.ridetogether.domain.dao;

import com.ridet.ridetogether.domain.Match;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * <h2>매칭이 완료된 Ride 간의 관계를 저장하는 Repository</h2>
 */
@Repository
public class MemoryMatchRepository implements MatchRepository {
    // matchId 는 uuid 사용 -> 외부에 노출되는 id이기 때문
    private Map<String, Match> store = new HashMap<>();

    @Override
    public String add(Match match) {
        String matchId = UUID.randomUUID().toString();
        match.setId(matchId);
        store.put(matchId, match);
        return matchId;
    }

    @Override
    public void remove(String id) {
        store.remove(id);
    }

    @Override
    public Optional<Match> findByMatchId(String id) {
        return Optional.of(store.get(id));
    }

    @Override
    public Optional<Match> findByUserId(int id) {
        // 저장된 match에서 userId가 있는 match찾기
        Match returnMatch = null;
        for (Match match : store.values()) {
            if (id == match.getUserId1() || id == match.getUserId2()) {
                returnMatch = match;
            }
        }

        if (returnMatch != null) {
            return Optional.of(returnMatch);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Match> findByRideId(int id) {
        Match returnMatch = null;
        for(Match match : store.values()) {
            if (id == match.getRideId1() || id == match.getRideId2()) {
                returnMatch = match;
            }
        }

        if (returnMatch != null) {
            return Optional.of(returnMatch);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int numOfCurrentMatch() {
        return store.size();
    }
}
