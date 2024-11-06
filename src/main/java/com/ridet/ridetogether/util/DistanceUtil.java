package com.ridet.ridetogether.util;

import com.ridet.ridetogether.domain.Location;

public class DistanceUtil {
    public static double getDistance(Location loc1, Location loc2) {
        double latitudeDistance = Math.toRadians(Math.abs(loc1.getLatitude() - loc2.getLatitude()));
        double longitudeDistance = Math.toRadians(Math.abs(loc1.getLongitude() - loc2.getLongitude()));

        double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2)
                + Math.cos(Math.toRadians(loc1.getLatitude())) * Math.cos(Math.toRadians(loc2.getLatitude()))
                * Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c * 1000; // 거리 m 단위
    }
}
