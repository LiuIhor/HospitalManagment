package com.hospitalManagement.repository;

import com.hospitalManagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByNumberRoom(int numberRoom);

    List<Room> findAllByHospitalHospitalId(Long hospitalId);

    List<Room> findAllByBookingStatusFalseAndHospitalHospitalId(Long hospital_id);

    List<Room> findAllByBookingStatusTrueAndHospitalHospitalId(Long hospital_id);

    List<Room> findAllByHospitalHospitalIdAndNumberFloor(Long hospitalId, int floor);
}
