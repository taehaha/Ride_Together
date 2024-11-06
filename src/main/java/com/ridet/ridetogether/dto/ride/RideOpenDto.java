package com.ridet.ridetogether.dto.ride;

public class RideOpenDto {
    // 현재위치 좌표
    private double currentLatitude;
    private double currentLongitude;

    // 목적지위치 좌표
    private double destinationLatitude;
    private double destinationLongtitude;

    public double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public double getDestinationLongtitude() {
        return destinationLongtitude;
    }

    public void setDestinationLongtitude(double destinationLongtitude) {
        this.destinationLongtitude = destinationLongtitude;
    }
}
