package com.hospitalManagement.service;

import com.hospitalManagement.dto.RoomDTO;
import com.hospitalManagement.entity.Room;

import java.util.List;

public interface RoomService {

    RoomDTO addRoom(RoomDTO room);

    void deleteRoom(Long id);

    RoomDTO getRoomById(Long room_id);

    RoomDTO editRoom(RoomDTO room);

    List<Room> getAllRooms();

    List<Room> getAllRoomsByHospitalId(Long hospital_id);

    List<Room> showFreeRooms(Long hospital_id);

    List<RoomDTO> showAllRoomFilterStatus(String book, Long hospitalId);

    void deleteRoomFromHospitalById(Long room_id);

    RoomDTO bookRoom(Long room_id);

    List<Room> showNotFreeRooms(Long hospitalId);
}
