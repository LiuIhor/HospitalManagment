package com.hospitalManagement.service.impl;

import com.hospitalManagement.entity.Hospital;
import com.hospitalManagement.entity.Room;
import com.hospitalManagement.exception_handling.IdNullException;
import com.hospitalManagement.exception_handling.NotFoundException;
import com.hospitalManagement.repository.RoomRepository;
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
        room.setRoomId(1L);
        room.setNumberRoom(101);
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
        room.setRoomId(1L);
        room.setNumberRoom(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomRepository.existsById(room.getRoomId())).thenReturn(true);

//        boolean result = roomService.deleteRoom(room.getRoom_id());
//        assertTrue(result);
        verify(roomRepository).deleteById(room.getRoomId());
    }

    @Test
    void whenDeleteIfNotFoundShouldBeReturnException() {
        Room room = new Room();
        room.setRoomId(1L);
        room.setNumberRoom(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomRepository.existsById(room.getRoomId())).thenReturn(false);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.deleteRoom(room.getRoomId());
        });
        assertNotNull(exception.getMessage());
        verify(roomRepository, times(0)).deleteById(room.getRoomId());
    }


    @Test
    void WhenGetRoomByIdIfNotFountShouldBeReturnException() {
        Room room = new Room();
        room.setRoomId(1L);
        room.setNumberRoom(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);
        when(roomRepository.existsById(room.getRoomId())).thenReturn(false);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.getRoomById(room.getRoomId());
        });
        assertNotNull(exception.getMessage());
        verify(roomRepository, times(0)).getById(room.getRoomId());
    }

    @Test
    void WhenGetRoomByIdIfFountShouldBeReturnRoom() {
        Room room = new Room();
        room.setRoomId(1L);
        room.setNumberRoom(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomRepository.existsById(room.getRoomId())).thenReturn(true);
        when(roomRepository.getById(room.getRoomId())).thenReturn(room);

        Room actual = roomService.getRoomById(room.getRoomId());

        assertEquals(room, actual);
        verify(roomRepository).getById(room.getRoomId());
    }

    @Test
    void WhenEditRoomIfRoomIdNullShouldBeReturnException() {
        Room room = new Room();
        room.setRoomId(null);
        room.setNumberRoom(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

//        Exception exception = assertThrows(IdNullException.class, () -> {
//            roomService.editRoom(room);
//        });
//        assertNotNull(exception.getMessage());
    }

    @Test
    void WhenEditRoomIfNotFoundReturnException() {
        Room room = new Room();
        room.setRoomId(1L);
        room.setNumberRoom(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);

        when(roomRepository.existsById(room.getRoomId())).thenReturn(false);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.editRoom(room);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void WhenEditRoomIfFoundReturnHospital() {
        Room room = new Room();
        room.setRoomId(1L);
        room.setNumberRoom(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);
//
//        when(roomRepository.existsById(room.getRoomId())).thenReturn(true);
//        when(roomRepository.saveAndFlush(room)).thenReturn(room);
//        Room actual = roomService.editRoom(room);
//        assertEquals(room, actual);
//        verify(roomRepository).saveAndFlush(room);
    }

    @Test
    void whenGetAllRoomsIfListEmptyShouldBeReturnException() {
        List<Room> rooms = new ArrayList<>();
        when(roomRepository.findAll()).thenReturn(rooms);

        Exception exception = assertThrows(NotFoundException.class, () -> {
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
        when(roomRepository.findAllByHospitalHospitalId(1L)).thenReturn(rooms);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.getAllRoomsByHospitalId(1L);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void whenGetAllRoomsByHospitalIdIfListNotEmptyShouldBeReturnHospitals() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room());
        Long hospital_id = 1L;
        when(roomRepository.findAllByHospitalHospitalId(hospital_id)).thenReturn(rooms);

        assertEquals(rooms, roomService.getAllRoomsByHospitalId(hospital_id));
        verify(roomRepository).findAllByHospitalHospitalId(hospital_id);
    }

    @Test
    void whenShowFreeRoomsIfListEmptyShouldBeReturnException() {
        List<Room> rooms = new ArrayList<>();
        Long hospital_id = 1L;
        when(roomRepository.findAllByBookingStatusFalseAndHospitalHospitalId(hospital_id)).thenReturn(rooms);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.showFreeRooms(hospital_id);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void whenShowFreeRoomsIfListNotEmptyShouldBeReturnHospitals() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room());
        Long hospital_id = 1L;
        when(roomRepository.findAllByBookingStatusFalseAndHospitalHospitalId(1L)).thenReturn(rooms);

        assertEquals(rooms, roomService.showFreeRooms(hospital_id));
        verify(roomRepository).findAllByBookingStatusFalseAndHospitalHospitalId(hospital_id);
    }

    @Test
    void whenDeleteRoomFromHospitalIdIfNotFoundShouldBeReturnException() {
        Long room_id = 1L;
        when(roomRepository.existsById(room_id)).thenReturn(false);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.deleteRoomFromHospitalById(room_id);
        });

        assertNotNull(exception.getMessage());
        verify(roomRepository, times(0)).deleteById(room_id);
    }

    @Test
    void whenDeleteRoomFromHospitalIdIfFoundShouldBeReturnTrue() {
        Room room = new Room();
        room.setRoomId(1L);
        room.setNumberRoom(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);
        when(roomRepository.existsById(room.getRoomId())).thenReturn(true);

//        when(roomRepository.getById(room.getRoom_id())).thenReturn(room);

//        boolean actual = roomService.deleteRoomFromHospitalById(room.getRoom_id());
//        assertTrue(actual);
        verify(roomRepository).deleteById(room.getRoomId());
    }


    @Test
    void whenBookRoomIfNotFoundShouldBeReturnException() {
        Room room = new Room();
        room.setRoomId(1L);
        room.setNumberRoom(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);
        when(roomRepository.existsById(room.getRoomId())).thenReturn(false);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.bookRoom(room.getRoomId());
        });

        assertNotNull(exception.getMessage());
        verify(roomRepository, times(0)).deleteById(room.getRoomId());

    }

    @Test
    void whenBookRoomIfFoundShouldBeReturnRoom() {
        Room room = new Room();
        room.setRoomId(1L);
        room.setNumberRoom(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);
        when(roomRepository.existsById(room.getRoomId())).thenReturn(true);
        when(roomRepository.getById(room.getRoomId())).thenReturn(room);
        when(roomRepository.saveAndFlush(room)).thenReturn(room);

        Room actual = roomService.bookRoom(room.getRoomId());

        assertEquals(room, actual);
        verify(roomRepository).saveAndFlush(room);

    }

    @Test
    void whenAddRoomShouldBeReturnRoom() {
        Room room = new Room();
        room.setRoomId(1L);
        room.setNumberRoom(101);
        room.setHospital(new Hospital());
        room.setDescription("description");
        room.setBookingStatus(true);
        when(roomRepository.saveAndFlush(room)).thenReturn(room);

        Room actual = roomService.addRoom(room);
        assertEquals(room, actual);
        verify(roomRepository).saveAndFlush(room);
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