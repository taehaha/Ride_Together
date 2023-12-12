package com.ridet.ridetogether.domain;

/**
 * <h2>위치 객체</h2>
 * <h4>위치정보를 가지는 객체</h4>
 */
// Latitude, Longtitude를 담는 도메인
public class Location {
    private double latitude;
    private double longtitude;

    public Location(double latitude, double longtitude) {
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
