package com.ridet.ridetogether.domain;

/**
 * <h2>위치 객체</h2>
 * <h4>위치정보를 가지는 객체</h4>
 */
// Latitude, Longitude를 담는 도메인
public class Location {
    private double latitude;
    private double longitude;

    public Location() {
    }

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

    /**
     * <p>두 위치의 중간위치를 반환합니다.</p>
     * @param loc1
     * @param loc2
     * @return 두 위치의 중앙값
     */
    public static Location middle(Location loc1, Location loc2) {
        double lat1 = loc1.getLatitude();
        double lat2 = loc2.getLatitude();

        double lng1 = loc1.getLongitude();
        double lng2 = loc2.getLongitude();

        return new Location.Builder()
                .latitude(Math.abs(lat1 - lat2))
                .longitude(Math.abs(lng1 - lng2))
                .build();
    }


    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
