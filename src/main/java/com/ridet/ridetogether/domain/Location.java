package com.ridet.ridetogether.domain;

/**
 * <h2>위치 객체</h2>
 * <h4>위치정보를 가지는 객체</h4>
 */
// Latitude, Longitude를 담는 도메인
public class Location {
    private double latitude;
    private double longitude;

    private Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public static class Builder {
        private double latitude;
        private double longitude;

        public Builder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Location build() {
            return new Location(this.latitude, this.longitude);
        }
    }
}
