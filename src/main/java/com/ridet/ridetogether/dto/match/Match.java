package com.ridet.ridetogether.dto.match;

import com.ridet.ridetogether.domain.Location;

import java.sql.Timestamp;

public class Match {
   Integer id;
   int rideId1;
   int rideId2;
   Location departure;
   Location destination;
   Timestamp matchedTimestamp;

   public Match() {}

   public Match(Integer id, int rideId1, int rideId2, Location departure, Location destination, Timestamp matchedTimestamp) {
      this.id = id;
      this.rideId1 = rideId1;
      this.rideId2 = rideId2;
      this.departure = departure;
      this.destination = destination;
      this.matchedTimestamp = matchedTimestamp;
   }

   public static class Builder {
      Integer id;
      int rideId1;
      int rideId2;
      Location departure;
      Location destination;
      Timestamp matchedTimestamp;

      public Builder rideId1(int rideId) {
         this.rideId1 = rideId;
         return this;
      }

      public Builder rideId2(int rideId) {
         this.rideId2 = rideId;
         return this;
      }

      public Builder departure(Location departure) {
         this.departure = departure;
         return this;
      }

      public Builder destinatioin(Location destination) {
         this.destination = destination;
         return this;
      }

      public Match build() {
         return new Match(id, rideId1, rideId2, departure, destination, matchedTimestamp);
      }
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
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

   public Timestamp getMatchedTimestamp() {
      return matchedTimestamp;
   }

   public void setMatchedTimestamp(Timestamp matchedTimestamp) {
      this.matchedTimestamp = matchedTimestamp;
   }

   @Override
   public String toString() {
      return "Match{" +
              "id=" + id +
              ", rideId1=" + rideId1 +
              ", rideId2=" + rideId2 +
              ", departure=" + departure +
              ", destination=" + destination +
              ", matchedTimestamp=" + matchedTimestamp +
              '}';
   }
}
