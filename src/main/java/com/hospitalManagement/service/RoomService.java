package com.hospitalManagement.service;

import com.hospitalManagement.entity.Room;

import java.util.List;

public interface RoomService {

    Room addRoom(Room room);

    void deleteRoom(Long id);

    Room getRoomById(Long room_id);

    Room editRoom(Room room);

    List<Room> getAllRooms();

    List<Room> getAllRoomsByHospitalId(Long hospital_id);

    List<Room> showFreeRooms(Long hospital_id);

    List<Room> showAllRoomFilterStatus(String book, Long hospitalId);

    void deleteRoomFromHospitalById(Long room_id);

    Room bookRoom(Long room_id);

    List<Room> showNotFreeRooms(Long hospitalId);
}
