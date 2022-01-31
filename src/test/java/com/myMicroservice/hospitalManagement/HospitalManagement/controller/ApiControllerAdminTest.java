package com.myMicroservice.hospitalManagement.HospitalManagement.controller;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.HospitalService;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApiControllerAdminTest {

    @Mock
    private HospitalService hospitalService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private ApiControllerAdmin apiControllerAdmin;

    @Test
    void showAllHospitals() {
        Hospital hospital = new Hospital();
        hospital.setHospital_id(1L);
        hospital.setPhone("+546874337298");
        hospital.setName("Name");
        hospital.setAddress("address");
        hospital.setEmail("email@gmail.com");
        hospital.setHospital_id(1L);

        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(hospital);
        when(hospitalService.getAllHospitals()).thenReturn(hospitals);

        List<Hospital> actual = apiControllerAdmin.showAllHospitals();

        assertEquals(hospitals, actual);
    }

    @Test
    void deleteHospitalById() {
        Long hospital_id = 1L;
        when(hospitalService.deleteHospital(hospital_id)).thenReturn(true);

        apiControllerAdmin.deleteHospitalById(hospital_id);
        verify(hospitalService).deleteHospital(hospital_id);
    }

    @Test
    void showHospitalById() {
        Hospital hospital = new Hospital();
        hospital.setHospital_id(1L);
        hospital.setAddress("str. Pushkinska 54");
        hospital.setDescription("The best hospital");
        hospital.setEmail("hospital@gmail.com");
        hospital.setName("Hospital of region");
        hospital.setPhone("+380956332496");
        when(hospitalService.getHospitalById(hospital.getHospital_id())).thenReturn(hospital);

        Hospital actual = apiControllerAdmin.showHospitalById(hospital.getHospital_id());
        assertEquals(hospital, actual);
        verify(hospitalService).getHospitalById(hospital.getHospital_id());
    }

    @Test
    void showAllRoomsByHospitalId() {
        Long hospital_id = 1L;
        List<Room> rooms = new ArrayList<>();
        Room room = new Room();
        room.setRoom_id(1L);
        rooms.add(room);
        when(roomService.getAllRoomsByHospitalId(hospital_id)).thenReturn(rooms);

        List<Room> actual = apiControllerAdmin.showAllRoomsByHospitalId(hospital_id);
        assertEquals(rooms, actual);
        verify(roomService).getAllRoomsByHospitalId(hospital_id);
    }

    @Test
    void deleteRoomFromHospitalById() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomService.deleteRoom(room.getRoom_id())).thenReturn(true);

        String actual = apiControllerAdmin.deleteRoomFromHospitalById(room.getRoom_id());
        String expected = "Room with id = " + room.getRoom_id() + " deleted";
        assertEquals(expected, actual);

        verify(roomService).deleteRoom(room.getRoom_id());
    }

    @Test
    void showRoomById() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomService.getRoomById(room.getRoom_id())).thenReturn(room);

        Room actual = apiControllerAdmin.showRoomById(room.getRoom_id());

        assertEquals(room, actual);

        verify(roomService).getRoomById(room.getRoom_id());
    }

    @Test
    void addRoom() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomService.addRoom(room)).thenReturn(room);

        Room actual = apiControllerAdmin.addRoom(room);

        assertEquals(room, actual);
        verify(roomService).addRoom(room);
    }

    @Test
    void changeRoom() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomService.editRoom(room)).thenReturn(room);

        Room actual = apiControllerAdmin.changeRoom(room);

        assertEquals(room, actual);
        verify(roomService).editRoom(room);
    }

    @Test
    void addHospital() {
        Long id = 1L;
        Hospital hospital = new Hospital();
        hospital.setHospital_id(id);
        hospital.setAddress("str. Pushkinska 54");
        hospital.setDescription("The best hospital");
        hospital.setEmail("hospital@gmail.com");
        hospital.setName("Hospital of region");
        hospital.setPhone("+380956332496");

        when(hospitalService.addHospital(hospital)).thenReturn(hospital);

        Hospital actual = apiControllerAdmin.addHospital(hospital);
        assertEquals(hospital, actual);
        verify(hospitalService).addHospital(hospital);
    }

    @Test
    void changeHospital() {

        Long id = 1L;
        Hospital hospital = new Hospital();
        hospital.setHospital_id(id);
        hospital.setAddress("str. Pushkinska 54");
        hospital.setDescription("The best hospital");
        hospital.setEmail("hospital@gmail.com");
        hospital.setName("Hospital of region");
        hospital.setPhone("+380956332496");
        when(hospitalService.editHospital(hospital)).thenReturn(hospital);

        Hospital actual = apiControllerAdmin.changeHospital(hospital);

        assertEquals(hospital, actual);
        verify(hospitalService).editHospital(hospital);
    }
}