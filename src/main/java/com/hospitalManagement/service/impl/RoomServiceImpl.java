package com.hospitalManagement.service.impl;

import com.hospitalManagement.dto.RoomDTO;
import com.hospitalManagement.entity.Room;
import com.hospitalManagement.exception_handling.IdNullException;
import com.hospitalManagement.exception_handling.NotFoundException;
import com.hospitalManagement.repository.RoomRepository;
import com.hospitalManagement.service.RoomService;
import com.hospitalManagement.utils.modelMapper.ConvertRoomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * @return Added room
     */
    @Override
    public RoomDTO addRoom(RoomDTO room) {
        return ConvertRoomUtil.convertToDTO(roomRepository.save(ConvertRoomUtil.convertToEntity(room)));
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
     * @return Room
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    @Override
    public RoomDTO getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() ->
                new NotFoundException(String.format("Room with id %d does not exists!", roomId)));
        return ConvertRoomUtil.convertToDTO(room);
    }

    /**
     * editRoom - method to edit room
     *
     * @param room - this is the Room object that will be received in the database
     * @return changed room
     * @throws IdNullException   - occurs when id equals null
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    @Override
    public RoomDTO editRoom(RoomDTO room) {
        if (!roomRepository.existsById(room.getRoomId())) {
            throw new NotFoundException(String.format("Room with id %d does not exists!", room.getRoomId()));
        }
        return ConvertRoomUtil.convertToDTO(roomRepository.save(ConvertRoomUtil.convertToEntity(room)));
    }

    /**
     * getAllRooms - method to get all rooms
     *
     * @return List of rooms
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        if (CollectionUtils.isEmpty(rooms)) {
            throw new NotFoundException("Rooms do not exists!");
        }
        return rooms;
    }

    /**
     * getAllRoomsByHospitalId - method to get all rooms by hospitalId
     *
     * @param hospitalId - id of the hospitals for which the search for rooms will be
     * @return List of rooms
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    @Override
    public List<Room> getAllRoomsByHospitalId(Long hospitalId) {
        List<Room> rooms = roomRepository.findAllByHospitalHospitalId(hospitalId);
        if (CollectionUtils.isEmpty(rooms)) {
            throw new NotFoundException(String.format("Rooms in hospital with id %d do not exists!", hospitalId));
        }
        return rooms;
    }

    /**
     * getAllRoomsByHospitalId - method to get all free rooms by hospitalId
     *
     * @param hospitalId - id of the hospitals for which the search for rooms will be
     * @return List of free rooms
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    @Override
    public List<Room> showFreeRooms(Long hospitalId) {
        List<Room> rooms = roomRepository.findAllByBookingStatusFalseAndHospitalHospitalId(hospitalId);
        if (CollectionUtils.isEmpty(rooms)) {
            throw new NotFoundException(String.format("Room in hospital with id %d does not exists!", hospitalId));
        }
        return rooms;
    }

    /**
     * getAllRoomsByHospitalId - method to get all not free rooms by hospitalId
     *
     * @param hospitalId - id of the hospitals for which the search for rooms will be
     * @return List of busy rooms
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    @Override
    public List<Room> showNotFreeRooms(Long hospitalId) {
        List<Room> rooms = roomRepository.findAllByBookingStatusTrueAndHospitalHospitalId(hospitalId);
        if (CollectionUtils.isEmpty(rooms)) {
            throw new NotFoundException(String.format("Room in hospital with id %d does not exists!", hospitalId));
        }
        return rooms;
    }

    /**
     * getAllRoomsByHospitalId - method to search by filter all rooms by hospitalId
     *
     * @param book       - bookingStatus of room
     * @param hospitalId - id of the hospitals for which the search for rooms will be
     * @return List of rooms
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    @Override
    public List<RoomDTO> showAllRoomFilterStatus(String book, Long hospitalId) {
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
        return rooms.stream().map(ConvertRoomUtil::convertToDTO).collect(Collectors.toList());
    }

    /**
     * bookRoom - method to booked by id room
     *
     * @param roomId - this is the id by which the Room object will book
     * @return Booked room
     * @throws NotFoundException - occurs when object is not found in the database
     */
    @Override
    public RoomDTO bookRoom(Long roomId) {
        Room bookRoom;
        if (roomRepository.existsById(roomId)) {
            bookRoom = roomRepository.getById(roomId);
            bookRoom.setBookingStatus(true);
            roomRepository.save(bookRoom);
        } else {
            throw new NotFoundException(String.format("Room with id %d does not exists", roomId));
        }
        return ConvertRoomUtil.convertToDTO(bookRoom);
    }

    /**
     * bookRoom - method to unbooked by id room
     *
     * @param roomId - this is the id by which the Room object will book
     * @return Booked room
     * @throws NotFoundException - occurs when object is not found in the database
     */
    @Override
    public RoomDTO unBookRoom(Long roomId) {
        Room unBookRoom;
        if (roomRepository.existsById(roomId)) {
            unBookRoom = roomRepository.getById(roomId);
            unBookRoom.setBookingStatus(false);
            roomRepository.save(unBookRoom);
        } else {
            throw new NotFoundException(String.format("Room with id %d does not exists", roomId));
        }
        return ConvertRoomUtil.convertToDTO(unBookRoom);
    }
}
