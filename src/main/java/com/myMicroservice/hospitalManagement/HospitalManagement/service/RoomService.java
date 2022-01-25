package com.myMicroservice.hospitalManagement.HospitalManagement.service;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoomService {
    Room addRoom(Room room);
    void deleteRoom(Long id);
    Room getRoomByNumberRoom(Long numberRoom);
    Room getRoomById(Long room_id);
    Room editRoom(Room room);
    List<Room> getAllRooms();
    List<Room> getAllRoomsByHospitalId(Long hospital_id);

    List<Room> showFreeRooms(Long hospital_id);

    void deleteRoomFromHospitalById(Long hospital_id, Long room_id);
}
