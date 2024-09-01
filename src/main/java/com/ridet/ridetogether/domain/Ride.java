package com.ridet.ridetogether.domain;

import java.util.Date;

/**
 * <h2>RIDE</h2>
 * <h4>사용자가 목적지를 지정하고 매칭을 누르면 생성되는 객체</h4>
 */
public class Ride {
    private Integer id; // Ride id
    private int userId; // 요청자
    private Location currentLocation; // 요청된 위치
    private Location destinationLocation; // 목적지
    private Date rideRequestDate; // 요청시각
    private boolean matched; // 매칭이 되었는지 확인

    public Ride() {
        currentLocation = new Location.Builder().build();
        destinationLocation = new Location.Builder().build();
    }

    public Ride(int userId, Location currentLocation, Location destinationLocation, Date rideRequestDate, boolean matched) {
        super();
        this.id = null;
        this.userId = userId;
        this.currentLocation = currentLocation;
        this.destinationLocation = destinationLocation;
        this.rideRequestDate = rideRequestDate;
        this.matched = matched;
    }

    public static class Builder {
        int userId;
        Location currentLocation;
        Location destinationLocation;
        Date rideRequestDate;
        boolean matched;

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
        public Builder rideRequestDate(Date date) {
            this.rideRequestDate = date;
            return this;
        }

        public Builder matched(boolean matched) {
            this.matched = matched;
            return this;
        }

        public Ride build() {
            return new Ride(userId, currentLocation, destinationLocation, rideRequestDate, false);
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

    public Date getRideRequestDate() {
        return rideRequestDate;
    }

    public void setRideRequestDate(Date rideRequestDate) {
        this.rideRequestDate = rideRequestDate;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}
