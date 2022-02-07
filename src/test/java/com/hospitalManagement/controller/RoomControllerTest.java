package com.hospitalManagement.controller;

import com.hospitalManagement.service.RoomService;
import com.hospitalManagement.entity.Hospital;
import com.hospitalManagement.entity.Room;
import com.hospitalManagement.service.HospitalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {
    @Mock
    private HospitalService hospitalService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @Test
    void showFreeRooms() {
        Long hospital_id = 1L;
        Room room = createRoom();
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        when(roomService.showFreeRooms(hospital_id)).thenReturn(rooms);

//        List<Room> actual = roomController.showFreeRooms(hospital_id);

//        assertEquals(rooms, actual);

        verify(roomService).showFreeRooms(hospital_id);
    }

    @Test
    void bookRoom() {
        Long room_id = 1L;
        Room room = createRoom();

        when(roomService.bookRoom(room_id)).thenReturn(room);

        Room actual = roomController.bookRoom(room_id);

        assertEquals(room, actual);

        verify(roomService, times(1)).bookRoom(room_id);
    }


    @Test
    void showAllRoomsByHospitalId() {
        Long hospital_id = 1L;
        List<Room> rooms = new ArrayList<>();
        Room room = createRoom();

        rooms.add(room);

        when(roomService.getAllRoomsByHospitalId(hospital_id)).thenReturn(rooms);

//        List<Room> actual = roomController.showAllRoomsByHospitalId(hospital_id);

//        assertEquals(rooms, actual);

        verify(roomService).getAllRoomsByHospitalId(hospital_id);
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

        when(roomService.getRoomById(room.getRoomId())).thenReturn(room);

//        Room actual = roomController.showRoomById(room.getRoomId());

//        assertEquals(room, actual);

        verify(roomService, times(1)).getRoomById(room.getRoomId());
    }

    @Test
    void addRoom() {
        Room room = createRoom();

        when(roomService.addRoom(room)).thenReturn(room);

//        Room actual = roomController.addRoom(room);

//        assertEquals(room, actual);
        verify(roomService, times(1)).addRoom(room);
    }

    @Test
    void changeRoom() {
        Room room = createRoom();

//        when(roomService.editRoom(room)).thenReturn(room);

//        Room actual = roomController.changeRoom(room);

//        assertEquals(room, actual);

//        verify(roomService, times(1)).editRoom(room);
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