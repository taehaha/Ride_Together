package com.ridet.ridetogether.domain;

import java.util.Date;

/**
 * <h2>매치 요청된 RIDE</h2>
 * <h4>사용자가 목적지를 지정하고 매칭을 누르면 생성되는 객체</h4>
 */
public class Ride {
    public Ride(User user, Location currentLocation, Location destinationLocation, Date rideRequestDate, Ride matchedRide) {
        this.user = user;
        this.currentLocation = currentLocation;
        this.destinationLocation = destinationLocation;
        this.rideRequestDate = rideRequestDate;
        this.matchedRide = matchedRide;
    }

    private User user; // 요청자
    private Location currentLocation; // 요청된 위치
    private Location destinationLocation; // 목적지
    private Date rideRequestDate; // 요청시각
    private Ride matchedRide; // 매칭이 된 Ride. if not matched : null


}
