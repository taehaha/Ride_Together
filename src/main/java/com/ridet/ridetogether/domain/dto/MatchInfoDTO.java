package com.ridet.ridetogether.domain.dto;

import com.ridet.ridetogether.domain.Location;
import org.springframework.http.HttpStatus;

public class MatchInfoDTO {
    String matchId;
    int userId1;
    int userId2;
    boolean matchActivated;

    Location departure;
    Location destination;

    HttpStatus status;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
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

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
