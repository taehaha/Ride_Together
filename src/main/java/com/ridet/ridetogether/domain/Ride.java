package com.ridet.ridetogether.domain;

import java.util.Date;

/**
 * <h2>매치 요청된 RIDE</h2>
 * <h4>사용자가 목적지를 지정하고 매칭을 누르면 생성되는 객체</h4>
 */
public class Ride {
    private int id; // Ride id
    private int userId; // 요청자
    private Location currentLocation; // 요청된 위치
    private Location destinationLocation; // 목적지
    private Date rideRequestDate; // 요청시각
    private Ride matchedRide; // 매칭이 된 Ride. if not matched : null

    public Ride(int id, int userId, Location currentLocation, Location destinationLocation, Date rideRequestDate, Ride matchedRide) {
        this.id = id;
        this.userId = userId;
        this.currentLocation = currentLocation;
        this.destinationLocation = destinationLocation;
        this.rideRequestDate = rideRequestDate;
        this.matchedRide = matchedRide;
    }

    public static void Builder() {
        int id;
        int userId;
        Location currentLocation;
        Location destinationLocation;
        Date rideRequestDate;
        Ride matchedRide;


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

    public Ride getMatchedRide() {
        return matchedRide;
    }

    public void setMatchedRide(Ride matchedRide) {
        this.matchedRide = matchedRide;
    }
}
