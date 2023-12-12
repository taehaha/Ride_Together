package com.ridet.ridetogether.domain.dto;

//TODO: Builder 추가
public class MatchOpenDTO {
    // 현재위치 좌표
    private double currentLatitude;
    private double currentLongtitude;

    // 목적지위치 좌표
    private double destinationLatitude;
    private double destinationLongtitude;

    public double getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public double getCurrentLongtitude() {
        return currentLongtitude;
    }

    public void setCurrentLongtitude(double currentLongtitude) {
        this.currentLongtitude = currentLongtitude;
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
