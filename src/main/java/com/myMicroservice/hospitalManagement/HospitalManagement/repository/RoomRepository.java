package com.myMicroservice.hospitalManagement.HospitalManagement.repository;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("from Room where number_room = :numberRoom")
    Room findByNumberRoom(@Param("numberRoom") Long numberRoom);

//    Room findRoomByNumber_room(Long numberRoom);

    @Query("from Room room where room.hospital.hospital_id = :hospital_id")
    List<Room> findByHospitalId(@Param("hospital_id") Long hospital_id);

    @Query("from Room room where room.hospital.hospital_id = :hospital_id and room.status = true")
    List<Room> findFreeRooms(Long hospital_id);
}
