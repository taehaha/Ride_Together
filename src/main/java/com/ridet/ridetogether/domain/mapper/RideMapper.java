package com.ridet.ridetogether.domain.mapper;

import com.ridet.ridetogether.domain.Location;
import com.ridet.ridetogether.domain.Ride;
import org.apache.ibatis.annotations.*;

import java.util.Date;

@Mapper
public interface RideMapper {
    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO rides (userId, currentLatitude, currentLongitude, destinationLatitude, destinationLongitude, rideRequestDate, matched)" +
            "VALUES (#{ride.userId}, #{ride.currentLocation.latitude}, #{ride.currentLocation.longitude}, #{ride.destinationLocation.latitude}, #{ride.destinationLocation.longitude}, #{ride.rideRequestDate}, #{ride.matched})")
    int open(@Param("ride") Ride ride);

    /**
     * MariaDB에서 rideId를 가진 ride 레코드를 삭제한다.
     * @param rideId
     * @return 삭제된 레코드의 개수
     */
    @Delete("DELETE FROM rides WHERE id = #{rideId}")
    int close(@Param("rideId") int rideId);

    @Select("SELECT * FROM rides WHERE id = #{rideId}")
    @Results({
            @Result(property = "currentLocation.latitude", column = "currentLatitude"),
            @Result(property = "currentLocation.longitude", column = "currentLongitude"),
            @Result(property = "destinationLocation.latitude", column = "destinationLatitude"),
            @Result(property = "destinationLocation.longitude", column = "destinationLongitude")
    })
    Ride findRideById(@Param("rideId") int rideId);

    @Select("SELECT * FROM rides WHERE userId = #{userId}")
    @Results({
            @Result(property = "currentLocation.latitude", column = "currentLatitude"),
            @Result(property = "currentLocation.longitude", column = "currentLongitude"),
            @Result(property = "destinationLocation.latitude", column = "destinationLatitude"),
            @Result(property = "destinationLocation.longitude", column = "destinationLongitude")
    })
    Ride findRideByUserId(@Param("userId") int userId);

}
