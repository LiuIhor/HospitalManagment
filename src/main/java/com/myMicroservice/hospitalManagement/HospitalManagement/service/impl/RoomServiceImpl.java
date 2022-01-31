package com.myMicroservice.hospitalManagement.HospitalManagement.service.impl;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;
import com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling.IdNullException;
import com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling.NoSuchDataException;
import com.myMicroservice.hospitalManagement.HospitalManagement.repository.RoomRepository;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.RoomService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room addRoom(Room room) {
        return roomRepository.saveAndFlush(room);
    }

    @Override
    public boolean deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            System.out.println("Room with id " + id + " founded!");
            roomRepository.deleteById(id);
        } else {
            System.out.println("You can`t delete this room. Because room with id " + id + " not founded!");
            throw new NoSuchDataException("You can`t delete this room. Because room with id " + id + " not founded!");
        }
        System.out.println("Room deleted");
        return true;
    }

    @Override
    public Room getRoomById(Long room_id) {
        Room room;
        if (roomRepository.existsById(room_id)) {
            System.out.println("Hospital with id " + room_id + " founded!");
            room = roomRepository.getById(room_id);
        } else {
            System.out.println("Room with id " + room_id + " not founded!");
            throw new NoSuchDataException("Room with id " + room_id + " not founded!");
        }
        return room;
    }

    @Override
    public Room editRoom(Room room) {
        if (room.getRoom_id() == null) {
            throw new IdNullException("The given id must not be null!");
        } else if (!roomRepository.existsById(room.getRoom_id())) {
            System.out.println("You can`t edit Room. Because room with id "
                    + room.getRoom_id() + " not founded!");
            throw new NoSuchDataException("You can`t edit Room. Because room with id "
                    + room.getRoom_id() + " not founded!");
        }
        return roomRepository.saveAndFlush(room);
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        if (rooms.isEmpty()) {
            throw new NoSuchDataException("table rooms in DB is empty");
        }
        return rooms;
    }

    @Override
    public List<Room> getAllRoomsByHospitalId(Long hospital_id) {
        List<Room> rooms = roomRepository.findByHospital_Hospital_id(hospital_id);
        if (rooms.isEmpty()) {
            throw new NoSuchDataException("Rooms of hospital with id " + hospital_id + " not founded!");
        }
        return rooms;
    }

    @Override
    public List<Room> showFreeRooms(Long hospital_id) {
        List<Room> rooms = roomRepository.findByBookingStatusFalse(hospital_id);
        if (rooms.isEmpty()) {
            throw new NoSuchDataException("Free rooms in hospital with id " + hospital_id + " not founded!");
        }
        return rooms;
    }

    @Override
    public boolean deleteRoomFromHospitalById(Long room_id) {
        System.out.println("Room with id " + room_id + " founded!");
        roomRepository.deleteById(room_id);
        return true;
    }

    @Override
    public Room bookRoom(Long room_id) {
        Room bookRoom;
        if (roomRepository.existsById(room_id)) {
            System.out.println("Room with id " + room_id + " founded!");
            bookRoom = roomRepository.getById(room_id);
            bookRoom.setBookingStatus(true);
            roomRepository.saveAndFlush(bookRoom);
        } else {
            System.out.println("You can`t book this room. Because room with id " + room_id + " not founded!");
            throw new NoSuchDataException("You can`t book this room. Because room with id " + room_id + " not founded!");
        }
        System.out.println("Room booked");
        return bookRoom;
    }
}
