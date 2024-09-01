package com.ridet.ridetogether.domain;

import java.util.Date;

public class Match {
    private String id; // UUID 사용
    private int userId1;
    private int userId2;
    private int rideId1;
    private int rideId2;
    private Location departure;
    private Location destination;
    private Date date;

    public Match(String id, int userId1, int userId2, int rideId1, int rideId2, Location departure, Location destination, Date date) {
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.rideId1 = rideId1;
        this.rideId2 = rideId2;
        this.departure = departure;
        this.destination = destination;
        this.date = date;
    }

    public static class Builder {
        private Integer id;
        private int userId1;
        private int userId2;
        private int rideId1;
        private int rideId2;
        private Location departure;
        private Location destination;
        private Date date;

        public Builder userId1(int userId1) {
            this.userId1 = userId1;
            return this;
        }
        public Builder userId2(int userId2) {
            this.userId2 = userId2;
            return this;
        }
        public Builder rideId1(int rideId1) {
            this.rideId1 = rideId1;
            return this;
        }
        public Builder rideId2(int rideId2) {
            this.rideId2 = rideId2;
            return this;
        }
        public Builder departure(Location location) {
            this.departure = location;
            return this;
        }
        public Builder destination(Location location) {
            this.destination = location;
            return this;
        }
        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Match build() {
            return new Match(null, userId1, userId2, rideId1, rideId2, departure, destination, date);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId2() {
        return userId2;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }

    public int getRideId1() {
        return rideId1;
    }

    public void setRideId1(int rideId1) {
        this.rideId1 = rideId1;
    }

    public int getRideId2() {
        return rideId2;
    }

    public void setRideId2(int rideId2) {
        this.rideId2 = rideId2;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
