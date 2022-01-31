package com.myMicroservice.hospitalManagement.HospitalManagement.repository;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("from Room where number_room = :numberRoom")
    Room findByNumberRoom(@Param("numberRoom") Long numberRoom);

//    Room findRoomByNumber_room(Long numberRoom);

    @Query("from Room room where room.hospital.hospital_id = :hospital_id")
    List<Room> findByHospital_Hospital_id(@Param("hospital_id") Long hospital_id);

    @Query("from Room room where room.hospital.hospital_id = :hospital_id and room.bookingStatus = true")
    List<Room> findByBookingStatusFalse(Long hospital_id);


}
