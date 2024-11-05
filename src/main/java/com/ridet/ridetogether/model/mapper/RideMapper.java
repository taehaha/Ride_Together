package com.ridet.ridetogether.model.mapper;

import com.ridet.ridetogether.dto.ride.Ride;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RideMapper {
    /**
     * Ride를 DB에 등록해 Matching을 시작한다.
     * ride.id에 DB에 등록된 id가 반환된다.
     * @param ride
     * @return 갱신된 Record 개수
     */
    int open(Ride ride);

    /**
     * MariaDB에서 rideId를 가진 ride 레코드를 삭제한다.
     * @param rideId
     * @return 삭제된 Record의 개수
     */
    int close(int rideId);

    Ride findRideById(int rideId);

    Ride findRideByUserId(int userId);
    List<Ride> selectAll();

    int numOfCurrentRide();
}
