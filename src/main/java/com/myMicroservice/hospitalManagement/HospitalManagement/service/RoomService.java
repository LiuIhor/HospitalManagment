package com.myMicroservice.hospitalManagement.HospitalManagement.service;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;

import java.util.List;

public interface RoomService {
    Room addRoom(Room room);

    boolean deleteRoom(Long id);

    Room getRoomById(Long room_id);

    Room editRoom(Room room);

    List<Room> getAllRooms();

    List<Room> getAllRoomsByHospitalId(Long hospital_id);

    List<Room> showFreeRooms(Long hospital_id);

    boolean deleteRoomFromHospitalById(Long room_id);

    Room bookRoom(Long room_id);
}
