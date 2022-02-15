package com.hospitalManagement.repository;

import com.hospitalManagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * Finds all rooms by number room
     *
     * @param numberRoom - this is the number room by which the Room object will be received
     * @return Room
     */
    Room findByNumberRoom(int numberRoom);

    /**
     * Finds all rooms by hospital id
     *
     * @param hospitalId - this is the hospital id by which the Room objects will be received
     * @return List of Room
     */
    List<Room> findAllByHospitalHospitalId(Long hospitalId);

    /**
     * Finds all rooms with status false by hospital id
     *
     * @param hospitalId - this is the hospital id by which the Room objects will be received
     * @return List of Room
     */
    List<Room> findAllByBookingStatusFalseAndHospitalHospitalId(Long hospitalId);

    /**
     * Finds all rooms with status true by hospital id
     *
     * @param hospitalId - this is the hospital id by which the Room objects will be received
     * @return List of Room
     */
    List<Room> findAllByBookingStatusTrueAndHospitalHospitalId(Long hospitalId);

    /**
     * Finds all rooms by hospital id and floor
     *
     * @param hospitalId - this is the hospital id by which the Room objects will be received
     * @param floor      - this is the floor by which the Room objects will be received
     * @return List of Room
     */
    List<Room> findAllByHospitalHospitalIdAndNumberFloor(Long hospitalId, int floor);
}
