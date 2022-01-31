package com.myMicroservice.hospitalManagement.HospitalManagement.service.impl;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;
import com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling.IdNullException;
import com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling.NoSuchDataException;
import com.myMicroservice.hospitalManagement.HospitalManagement.repository.RoomRepository;
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
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;
    @Test
    void whenSaveHospitalShouldReturnHospital() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomRepository.saveAndFlush(room)).thenReturn(room);
        assertEquals(room, roomService.addRoom(room));
        verify(roomRepository).saveAndFlush(room);
    }
    @Test
    void whenDeleteIfFoundShouldBeReturnTrue() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomRepository.existsById(room.getRoom_id())).thenReturn(true);

        boolean result = roomService.deleteRoom(room.getRoom_id());
        assertTrue(result);
        verify(roomRepository).deleteById(room.getRoom_id());
    }

    @Test
    void whenDeleteIfNotFoundShouldBeReturnException() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomRepository.existsById(room.getRoom_id())).thenReturn(false);
        Exception exception = assertThrows(NoSuchDataException.class, () -> {
            roomService.deleteRoom(room.getRoom_id());
        });
        assertNotNull(exception.getMessage());
        verify(roomRepository, times(0)).deleteById(room.getRoom_id());
    }


    @Test
    void WhenGetRoomByIdIfNotFountShouldBeReturnException() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);
        when(roomRepository.existsById(room.getRoom_id())).thenReturn(false);
        Exception exception = assertThrows(NoSuchDataException.class, () -> {
            roomService.getRoomById(room.getRoom_id());
        });
        assertNotNull(exception.getMessage());
        verify(roomRepository, times(0)).getById(room.getRoom_id());
    }

    @Test
    void WhenGetRoomByIdIfFountShouldBeReturnRoom() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomRepository.existsById(room.getRoom_id())).thenReturn(true);
        when(roomRepository.getById(room.getRoom_id())).thenReturn(room);

        Room actual = roomService.getRoomById(room.getRoom_id());

        assertEquals(room, actual);
        verify(roomRepository).getById(room.getRoom_id());
    }

    @Test
    void WhenEditRoomIfRoomIdNullShouldBeReturnException() {
        Room room = new Room();
        room.setRoom_id(null);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        Exception exception = assertThrows(IdNullException.class, () -> {
            roomService.editRoom(room);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void WhenEditRoomIfNotFoundReturnException() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomRepository.existsById(room.getRoom_id())).thenReturn(false);
        Exception exception = assertThrows(NoSuchDataException.class, () -> {
            roomService.editRoom(room);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void WhenEditRoomIfFoundReturnHospital() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomRepository.existsById(room.getRoom_id())).thenReturn(true);
        when(roomRepository.saveAndFlush(room)).thenReturn(room);
        Room actual = roomService.editRoom(room);
        assertEquals(room, actual);
        verify(roomRepository).saveAndFlush(room);
    }

    @Test
    void whenGetAllRoomsIfListEmptyShouldBeReturnException() {
        List<Room> rooms = new ArrayList<>();
        when(roomRepository.findAll()).thenReturn(rooms);

        Exception exception = assertThrows(NoSuchDataException.class, () -> {
            roomService.getAllRooms();
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void whenGetAllRoomsIfListNotEmptyShouldBeReturnHospitals() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room());
        when(roomRepository.findAll()).thenReturn(rooms);

        assertEquals(rooms, roomService.getAllRooms());
        verify(roomRepository).findAll();
    }

    @Test
    void whenGetAllRoomsByHospitalIdIfListEmptyShouldBeReturnException() {
        List<Room> rooms = new ArrayList<>();
        when(roomRepository.findByHospital_Hospital_id(1L)).thenReturn(rooms);

        Exception exception = assertThrows(NoSuchDataException.class, () -> {
            roomService.getAllRoomsByHospitalId(1L);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void whenGetAllRoomsByHospitalIdIfListNotEmptyShouldBeReturnHospitals() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room());
        Long hospital_id = 1L;
        when(roomRepository.findByHospital_Hospital_id(hospital_id)).thenReturn(rooms);

        assertEquals(rooms, roomService.getAllRoomsByHospitalId(hospital_id));
        verify(roomRepository).findByHospital_Hospital_id(hospital_id);
    }

    @Test
    void whenShowFreeRoomsIfListEmptyShouldBeReturnException() {
        List<Room> rooms = new ArrayList<>();
        Long hospital_id = 1L;
        when(roomRepository.findByBookingStatusFalse(hospital_id)).thenReturn(rooms);

        Exception exception = assertThrows(NoSuchDataException.class, () -> {
            roomService.showFreeRooms(hospital_id);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void whenShowFreeRoomsIfListNotEmptyShouldBeReturnHospitals() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room());
        Long hospital_id = 1L;
        when(roomRepository.findByBookingStatusFalse(1L)).thenReturn(rooms);

        assertEquals(rooms, roomService.showFreeRooms(hospital_id));
        verify(roomRepository).findByBookingStatusFalse(hospital_id);
    }

    @Test
    void whenDeleteRoomFromHospitalIdIfNotFoundShouldBeReturnException() {
        Long room_id = 1L;
        when(roomRepository.existsById(room_id)).thenReturn(false);

        Exception exception = assertThrows(NoSuchDataException.class, () -> {
            roomService.deleteRoomFromHospitalById(room_id);
        });

        assertNotNull(exception.getMessage());
        verify(roomRepository, times(0)).deleteById(room_id);
    }

    @Test
    void whenDeleteRoomFromHospitalIdIfFoundShouldBeReturnTrue() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);
        when(roomRepository.existsById(room.getRoom_id())).thenReturn(true);

//        when(roomRepository.getById(room.getRoom_id())).thenReturn(room);

        boolean actual = roomService.deleteRoomFromHospitalById(room.getRoom_id());
        assertTrue(actual);
        verify(roomRepository).deleteById(room.getRoom_id());
    }


    @Test
    void whenBookRoomIfNotFoundShouldBeReturnException() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);
        when(roomRepository.existsById(room.getRoom_id())).thenReturn(false);

        Exception exception = assertThrows(NoSuchDataException.class, () -> {
            roomService.bookRoom(room.getRoom_id());
        });

        assertNotNull(exception.getMessage());
        verify(roomRepository, times(0)).deleteById(room.getRoom_id());

    }

    @Test
    void whenBookRoomIfFoundShouldBeReturnRoom() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);
        when(roomRepository.existsById(room.getRoom_id())).thenReturn(true);
        when(roomRepository.getById(room.getRoom_id())).thenReturn(room);
        when(roomRepository.saveAndFlush(room)).thenReturn(room);

        Room actual = roomService.bookRoom(room.getRoom_id());

        assertEquals(room, actual);
        verify(roomRepository).saveAndFlush(room);

    }

    @Test
    void whenAddRoomShouldBeReturnRoom() {
        Room room = new Room();
        room.setRoom_id(1L);
        room.setNumber_room(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);
        when(roomRepository.saveAndFlush(room)).thenReturn(room);

        Room actual = roomService.addRoom(room);
        assertEquals(room, actual);
        verify(roomRepository).saveAndFlush(room);
    }
}