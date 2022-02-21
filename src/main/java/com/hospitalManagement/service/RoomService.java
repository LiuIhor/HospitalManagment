package com.hospitalManagement.service;

import com.hospitalManagement.dto.RoomDTO;
import com.hospitalManagement.entity.Room;
import com.hospitalManagement.exception_handling.IdNullException;
import com.hospitalManagement.exception_handling.NotFoundException;

import java.util.List;

/**
 * RoomService - Service with business logic for Rooms
 */
public interface RoomService {

    /**
     * addHospital - method to add room in the DB
     *
     * @param room - this is the Room object that will be recorded in the database
     * @return Added room
     */
    RoomDTO addRoom(RoomDTO room);

    /**
     * deleteRoom - method to delete by id room from theDB
     *
     * @param id - this is the id by which the Room object will be deleted
     */
    void deleteRoom(Long id);

    /**
     * getRoomById - method to get room by id from theDB
     *
     * @param roomId - this is the id by which the Room object will be received
     * @return Room
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    RoomDTO getRoomById(Long roomId);

    /**
     * editRoom - method to edit room
     *
     * @param room - this is the Room object that will be received in the database
     * @return changed room
     * @throws IdNullException   - occurs when id equals null
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    RoomDTO editRoom(RoomDTO room);

    /**
     * getAllRooms - method to get all rooms
     *
     * @return List of rooms
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    List<Room> getAllRooms();

    /**
     * getAllRoomsByHospitalId - method to get all rooms by hospitalId
     *
     * @param hospitalId - id of the hospitals for which the search for rooms will be
     * @return List of rooms
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    List<Room> getAllRoomsByHospitalId(Long hospitalId);

    /**
     * getAllRoomsByHospitalId - method to get all free rooms by hospitalId
     *
     * @param hospitalId - id of the hospitals for which the search for rooms will be
     * @return List of free rooms
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    List<Room> showFreeRooms(Long hospitalId);

    /**
     * getAllRoomsByHospitalId - method to search by filter all rooms by hospitalId
     *
     * @param book       - bookingStatus of room
     * @param hospitalId - id of the hospitals for which the search for rooms will be
     * @return List of rooms
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    List<RoomDTO> showAllRoomFilterStatus(String book, Long hospitalId);

    /**
     * bookRoom - method to booked by id room
     *
     * @param roomId - this is the id by which the Room object will book
     * @return Booked room
     * @throws NotFoundException - occurs when object is not found in the database
     */
    RoomDTO bookRoom(Long roomId);

    /**
     * bookRoom - method to unbooked by id room
     *
     * @param roomId - this is the id by which the Room object will book
     * @return Booked room
     * @throws NotFoundException - occurs when object is not found in the database
     */
    RoomDTO unBookRoom(Long roomId);

    /**
     * getAllRoomsByHospitalId - method to get all not free rooms by hospitalId
     *
     * @param hospitalId - id of the hospitals for which the search for rooms will be
     * @return List of busy rooms
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    List<Room> showNotFreeRooms(Long hospitalId);
}
