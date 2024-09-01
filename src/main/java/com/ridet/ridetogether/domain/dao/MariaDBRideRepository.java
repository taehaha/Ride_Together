package com.ridet.ridetogether.domain.dao;

import com.ridet.ridetogether.domain.Ride;
import com.ridet.ridetogether.domain.mapper.RideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MariaDBRideRepository implements RideRepository {
    private final RideMapper rideMapper;

    @Autowired
    public MariaDBRideRepository(RideMapper rideMapper) {
        this.rideMapper = rideMapper;
    }

    /**
     * <p>Ride instance를 MariaDB에 저장한다.</p>
     * <p>저장이 완료 되면 DB상에 생성된 Ride.id 값을 넣어 return 해 준다.</p>
     * @param ride : DB에 저장할 Ride instance.
     * @return ride : ride.id 가 추가돼 있음
     */
    @Override
    public Ride open(Ride ride) {
        int id = rideMapper.open(ride);
        ride.setId(id);
        return ride;
    }

    /**
     * <p>rideId를 가진 ride 레코드를 MariaDB에서 삭제한다.</p>
     * @param rideId
     */
    @Override
    public void close(int rideId) {
        int numOfDeletedRide = rideMapper.close(rideId);
    }

    @Override
    public void updateRide(Ride ride) {

    }

    @Override
    public void setMatched(int rideId1, int rideId2) {

    }

    @Override
    public Ride[] getRides() {
        return new Ride[0];
    }

    @Override
    public Optional<Ride> findRideById(int id) {
        Ride ride = rideMapper.findRideById(id);
        return Optional.ofNullable(ride);
    }

    @Override
    public Optional<Ride> findRideByUserId(int id) {
        Ride ride = rideMapper.findRideByUserId(id);
        return Optional.ofNullable(ride);
    }

    @Override
    public int numOfCurrentRide() {
        return 0;
    }
}
