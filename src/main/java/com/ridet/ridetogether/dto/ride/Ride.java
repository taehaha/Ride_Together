package com.ridet.ridetogether.dto.ride;


import com.ridet.ridetogether.domain.Location;

import java.sql.Timestamp;

/**
 * <h2>RIDE</h2>
 * <h4>사용자가 목적지를 지정하고 매칭을 누르면 생성되는 객체</h4>
 */
public class Ride {
    private Integer id; // Ride id
    private int userId; // 요청자
    private Location currentLocation; // 요청된 위치
    private Location destinationLocation; // 목적지
    private Timestamp requestDate; // 요청시각

    public Ride() {
        currentLocation = new Location.Builder().build();
        destinationLocation = new Location.Builder().build();
    }

    public Ride(int userId, Location currentLocation, Location destinationLocation, Timestamp requestDate) {
        super();
        this.id = null;
        this.userId = userId;
        this.currentLocation = currentLocation;
        this.destinationLocation = destinationLocation;
        this.requestDate = requestDate;
    }

    public static class Builder {
        int userId;
        Location currentLocation;
        Location destinationLocation;

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder currentLocation(Location currentLocation) {
            this.currentLocation = currentLocation;
            return this;
        }

        public Builder destinationLocation(Location destinationLocation) {
            this.destinationLocation = destinationLocation;
            return this;
        }

        public Ride build() {
            return new Ride(userId, currentLocation, destinationLocation, null);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Location getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(Location destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", userId=" + userId +
                ", currentLocation=" + currentLocation +
                ", destinationLocation=" + destinationLocation +
                ", requestDate=" + requestDate +
                '}';
    }
}
