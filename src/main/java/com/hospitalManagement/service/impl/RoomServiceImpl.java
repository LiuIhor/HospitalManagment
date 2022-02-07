package com.hospitalManagement.service.impl;

import com.hospitalManagement.entity.Room;
import com.hospitalManagement.exception_handling.IdNullException;
import com.hospitalManagement.exception_handling.NotFoundException;
import com.hospitalManagement.repository.RoomRepository;
import com.hospitalManagement.service.RoomService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * RoomService - Service with business logic for Rooms
 */
@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    /**
     * addHospital - method to add room in the DB
     *
     * @param room - this is the Room object that will be recorded in the database
     */
    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    /**
     * deleteRoom - method to delete by id room from theDB
     *
     * @param id - this is the id by which the Room object will be deleted
     */
    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    /**
     * getRoomById - method to get room by id from theDB
     *
     * @param roomId - this is the id by which the Room object will be received
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    @Override
    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() ->
                new NotFoundException(String.format("I don`t found room with id %d", roomId)));
    }

    /**
     * editRoom - method to edit room
     *
     * @param room - this is the Room object that will be received in the database
     * @throws IdNullException   - occurs when id equals null
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    @Override
    public Room editRoom(Room room) {
        if (!roomRepository.existsById(room.getRoomId())) {
            throw new NotFoundException(String.format("You can`t edit Room. " +
                    "Because room with id %d not founded!", room.getRoomId()));
        }
        return roomRepository.save(room);
    }

    /**
     * getAllRooms - method to get all rooms
     *
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        if (rooms.isEmpty()) {
            throw new NotFoundException("table rooms in DB is empty");
        }
        return rooms;
    }

    /**
     * getAllRoomsByHospitalId - method to get all rooms by hospitalId
     *
     * @param hospitalId - id of the hospitals for which the search for rooms will be
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    @Override
    public List<Room> getAllRoomsByHospitalId(Long hospitalId) {
        List<Room> rooms = roomRepository.findAllByHospitalHospitalId(hospitalId);
        if (rooms.isEmpty()) {
            throw new NotFoundException(String.format("Rooms of hospital with id %d not founded!", hospitalId));
        }
        return rooms;
    }

    /**
     * getAllRoomsByHospitalId - method to get all free rooms by hospitalId
     *
     * @param hospitalId - id of the hospitals for which the search for rooms will be
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    @Override
    public List<Room> showFreeRooms(Long hospitalId) {
        List<Room> rooms = roomRepository.findAllByBookingStatusFalseAndHospitalHospitalId(hospitalId);
        if (rooms.isEmpty()) {
            throw new NotFoundException(String.format("Free rooms in hospital with id %d not founded!", hospitalId));
        }
        return rooms;
    }

    /**
     * getAllRoomsByHospitalId - method to get all not free rooms by hospitalId
     *
     * @param hospitalId - id of the hospitals for which the search for rooms will be
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    @Override
    public List<Room> showNotFreeRooms(Long hospitalId) {
        List<Room> rooms = roomRepository.findAllByBookingStatusTrueAndHospitalHospitalId(hospitalId);
        if (rooms.isEmpty()) {
            throw new NotFoundException(String.format("Free rooms in hospital with id %d not founded!", hospitalId));
        }
        return rooms;
    }

    /**
     * getAllRoomsByHospitalId - method to search by filter all rooms by hospitalId
     *
     * @param book       - bookingStatus of room
     * @param hospitalId - id of the hospitals for which the search for rooms will be
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    @Override
    public List<Room> showAllRoomFilterStatus(String book, Long hospitalId) {
        List<Room> rooms = new ArrayList<>();
        if (book.equals("false")) {
            rooms = showFreeRooms(hospitalId);
        }
        if (book.equals("true")) {
            rooms = showNotFreeRooms(hospitalId);
        }
        if (book.equals("all")) {
            rooms = getAllRoomsByHospitalId(hospitalId);
        }
        return rooms;
    }

    /**
     * deleteRoomFromHospitalById - method to delete by id room from the DB
     *
     * @param roomId - this is the id by which the Room object will be deleted
     */
    @Override
    public void deleteRoomFromHospitalById(Long roomId) {
        roomRepository.deleteById(roomId);
    }

    /**
     * bookRoom - method to booked by id room
     *
     * @param roomId - this is the id by which the Room object will book
     * @throws NotFoundException - occurs when object is not found in the database
     */
    @Override
    public Room bookRoom(Long roomId) {
        Room bookRoom;
        if (roomRepository.existsById(roomId)) {
            bookRoom = roomRepository.getById(roomId);
            bookRoom.setBookingStatus(true);
            roomRepository.save(bookRoom);
        } else {
            throw new NotFoundException(String.format("You can`t book this room. " +
                    "Because room with id %d not founded!", roomId));
        }
        return bookRoom;
    }
}
