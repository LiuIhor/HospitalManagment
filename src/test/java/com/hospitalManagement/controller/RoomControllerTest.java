package com.hospitalManagement.controller;

import com.hospitalManagement.dto.RoomDTO;
import com.hospitalManagement.service.RoomService;
import com.hospitalManagement.entity.Hospital;
import com.hospitalManagement.entity.Room;
import com.hospitalManagement.utils.ConvertRoomUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @Test
    void bookRoom() {
        Long room_id = 1L;
        Room room = createRoom();

        when(roomService.bookRoom(room_id)).thenReturn(ConvertRoomUtils.convertToDTO(room));

        RoomDTO actual = roomController.bookRoom(room_id);

        assertEquals(ConvertRoomUtils.convertToDTO(room), actual);

        verify(roomService, times(1)).bookRoom(room_id);
    }


    @Test
    void showAllRoomsByHospitalId() {
        Long hospital_id = 1L;
        List<Room> rooms = new ArrayList<>();

        Room room = createRoom();

        room.setBookingStatus(true);

        rooms.add(room);

        when(roomService.showAllRoomFilterStatus("true", hospital_id))
                .thenReturn(rooms.stream()
                        .map(ConvertRoomUtils::convertToDTO)
                        .collect(Collectors.toList()));

        List<Room> actual = roomController.showAllRoomsByHospitalId(hospital_id, "true").stream()
                .map(ConvertRoomUtils::convertToEntity)
                .collect(Collectors.toList());

        assertEquals(rooms, actual);

        verify(roomService).showAllRoomFilterStatus("true", hospital_id);
    }

    @Test
    void deleteRoomFromHospitalById() {
        Room room = createRoom();

        doNothing().when(roomService).deleteRoom(room.getRoomId());

        roomController.deleteRoomFromHospitalById(room.getRoomId());

        verify(roomService, times(1)).deleteRoom(room.getRoomId());
    }

    @Test
    void showRoomById() {
        Room room = createRoom();

        when(roomService.getRoomById(room.getRoomId())).thenReturn(ConvertRoomUtils.convertToDTO(room));

        Room actual = ConvertRoomUtils.convertToEntity(roomController.showRoomById(room.getRoomId()));

        assertEquals(room, actual);

        verify(roomService, times(1)).getRoomById(room.getRoomId());
    }

    @Test
    void addRoom() {
        Room room = createRoom();

        when(roomService.addRoom(ConvertRoomUtils.convertToDTO(room)))
                .thenReturn(ConvertRoomUtils.convertToDTO(room));

        RoomDTO actual = roomController.addRoom(ConvertRoomUtils.convertToDTO(room));

        assertEquals(ConvertRoomUtils.convertToDTO(room), actual);

        verify(roomService, times(1)).addRoom(ConvertRoomUtils.convertToDTO(room));
    }

    @Test
    void changeRoom() {
        Room room = createRoom();

        long roomId = 1L;

        when(roomService.editRoom(ConvertRoomUtils.convertToDTO(room)))
                .thenReturn(ConvertRoomUtils.convertToDTO(room));

        RoomDTO actual = roomController.changeRoom(roomId, ConvertRoomUtils.convertToDTO(room));

        assertEquals(ConvertRoomUtils.convertToDTO(room), actual);

        verify(roomService, times(1)).editRoom(ConvertRoomUtils.convertToDTO(room));
    }

    Room createRoom() {
        Room room = new Room();
        Hospital hospital = new Hospital();
        hospital.setHospitalId(1L);
        room.setRoomId(1L);
        room.setNumberRoom(101);
        room.setNumberFloor(1);
        room.setHospital(hospital);
        room.setDescription("description");
        room.setBookingStatus(false);
        return room;
    }

}