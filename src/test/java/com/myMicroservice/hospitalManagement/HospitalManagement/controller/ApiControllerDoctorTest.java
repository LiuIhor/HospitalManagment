package com.myMicroservice.hospitalManagement.HospitalManagement.controller;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.HospitalService;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiControllerDoctorTest {
    @Mock
    private HospitalService hospitalService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private ApiControllerDoctor apiControllerDoctor;

    @Test
    void showFreeRooms() {
        Long hospital_id = 1L;
        Room room = new Room();
        Hospital hospital = new Hospital();
        hospital.setHospital_id(hospital_id);
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(hospital);
        room.setDescription("description");
        room.setBookingStatus(false);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        when(roomService.showFreeRooms(hospital_id)).thenReturn(rooms);

        List<Room> actual = apiControllerDoctor.showFreeRooms(hospital_id);
        assertEquals(rooms, actual);
        verify(roomService).showFreeRooms(hospital_id);
    }

    @Test
    void bookRoom() {
        Long room_id = 1L;
        Long hospital_id = 1L;
        Room room = new Room();
        Hospital hospital = new Hospital();
        hospital.setHospital_id(hospital_id);
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(hospital);
        room.setDescription("description");
        room.setBookingStatus(true);
        when(roomService.bookRoom(room_id)).thenReturn(room);

        Room actual = apiControllerDoctor.bookRoom(room_id);
        assertEquals(room, actual);
    }
}