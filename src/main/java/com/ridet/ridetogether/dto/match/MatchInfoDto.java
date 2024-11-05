package com.ridet.ridetogether.dto.match;

import com.ridet.ridetogether.domain.Location;
import org.springframework.http.HttpStatus;

public class MatchInfoDto {
    Location departure;
    Location destination;

    public MatchInfoDto() {
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
}
