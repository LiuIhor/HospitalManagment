package com.myMicroservice.hospitalManagement.HospitalManagement.service.impl;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;
import com.myMicroservice.hospitalManagement.HospitalManagement.repository.HospitalRepository;
import com.myMicroservice.hospitalManagement.HospitalManagement.repository.RoomRepository;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.HospitalService;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room addRoom(Room room) {
        Room savedRoom = roomRepository.saveAndFlush(room);
        return savedRoom;
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room getRoomByNumberRoom(Long numberRoom) {
        return roomRepository.findByNumberRoom(numberRoom);
    }

    @Override
    public Room getRoomById(Long room_id) {
        return roomRepository.getById(room_id);
    }

    @Override
    public Room editRoom(Room room) {
        return roomRepository.saveAndFlush(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getAllRoomsByHospitalId(Long hospital_id) {
        return roomRepository.findByHospitalId(hospital_id);
    }

    @Override
    public List<Room> showFreeRooms(Long hospital_id) {
        return roomRepository.findFreeRooms(hospital_id);
    }
// Check
    @Override
    public void deleteRoomFromHospitalById(Long hospital_id, Long room_id) {

    }
}
