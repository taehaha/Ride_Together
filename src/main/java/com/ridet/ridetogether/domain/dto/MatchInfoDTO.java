package com.ridet.ridetogether.domain.dto;

import com.ridet.ridetogether.domain.Location;
import org.springframework.http.HttpStatus;

public class MatchInfoDTO {
    String matchId;
    Integer userId1;
    Integer userId2;
    boolean matchActivated;

    Location departure;
    Location destination;
    Location matchDeparture;
    Location matchDestination;

    HttpStatus status;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public Integer getUserId1() {
        return userId1;
    }

    public void setUserId1(Integer userId1) {
        this.userId1 = userId1;
    }

    public Integer getUserId2() {
        return userId2;
    }

    public void setUserId2(Integer userId2) {
        this.userId2 = userId2;
    }

    public boolean isMatchActivated() {
        return matchActivated;
    }

    public void setMatchActivated(boolean matchActivated) {
        this.matchActivated = matchActivated;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Location getMatchDeparture() {
        return matchDeparture;
    }

    public void setMatchDeparture(Location matchDeparture) {
        this.matchDeparture = matchDeparture;
    }

    public Location getMatchDestination() {
        return matchDestination;
    }

    public void setMatchDestination(Location matchDestination) {
        this.matchDestination = matchDestination;
    }
}
