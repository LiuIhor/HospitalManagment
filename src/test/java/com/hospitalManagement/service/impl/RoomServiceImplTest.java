package com.hospitalManagement.service.impl;

import com.hospitalManagement.entity.Hospital;
import com.hospitalManagement.entity.Room;
import com.hospitalManagement.exception_handling.NotFoundException;
import com.hospitalManagement.repository.RoomRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Room room = createRoom();

        when(roomRepository.save(room)).thenReturn(room);

        assertEquals(room, roomService.addRoom(room));

        verify(roomRepository, times(1)).save(room);
    }

    @Test
    void whenDelete() {
        Long id = 1L;

        doNothing().when(roomRepository).deleteById(id);

        roomService.deleteRoom(id);

        verify(roomRepository, times(1)).deleteById(id);
    }

    @Test
    void whenGetRoomByIdIfNotFountShouldBeReturnException() {
        Room room = createRoom();

        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.getRoomById(room.getRoomId());
        });

        assertNotNull(exception.getMessage());

        verify(roomRepository, times(1)).findById(room.getRoomId());
    }

    @Test
    void whenGetRoomByIdIfFountShouldBeReturnRoom() {
        Room room = createRoom();

        when(roomRepository.findById(room.getRoomId())).thenReturn(Optional.of(room));

        Room actual = roomService.getRoomById(room.getRoomId());

        assertEquals(room, actual);

        verify(roomRepository, times(1)).findById(room.getRoomId());
    }

    @Test
    void whenEditRoomIfNotFoundReturnException() {
        Room room = createRoom();

        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.editRoom(room);
        });

        assertNotNull(exception.getMessage());

        verify(roomRepository, times(0)).save(room);
    }

    @Test
    void WhenEditRoomIfFoundReturnHospital() {
        Room room = createRoom();

        when(roomRepository.existsById(room.getRoomId())).thenReturn(true);

        when(roomRepository.save(room)).thenReturn(room);

        Room actual = roomService.editRoom(room);

        assertEquals(room, actual);

        verify(roomRepository, times(1)).save(room);
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

        rooms.add(createRoom());

        when(roomRepository.findAll()).thenReturn(rooms);

        assertEquals(rooms, roomService.getAllRooms());

        verify(roomRepository, times(1)).findAll();
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
        rooms.add(createRoom());
        Long hospitalId = 1L;

        when(roomRepository.findAllByHospitalHospitalId(hospitalId)).thenReturn(rooms);

        assertEquals(rooms, roomService.getAllRoomsByHospitalId(hospitalId));

        verify(roomRepository).findAllByHospitalHospitalId(hospitalId);
    }

    @Test
    void whenShowFreeRoomsIfListEmptyShouldBeReturnException() {
        List<Room> rooms = new ArrayList<>();
        Long hospitalId = 1L;

        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.showFreeRooms(hospitalId);
        });

        assertNotNull(exception.getMessage());
    }

    @Test
    void whenShowFreeRoomsIfListNotEmptyShouldBeReturnHospitals() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(createRoom());
        Long hospitalId = 1L;

        when(roomRepository.findAllByBookingStatusFalseAndHospitalHospitalId(1L)).thenReturn(rooms);

        assertEquals(rooms, roomService.showFreeRooms(hospitalId));

        verify(roomRepository).findAllByBookingStatusFalseAndHospitalHospitalId(hospitalId);
    }

    @Test
    void whenBookRoomIfNotFoundShouldBeReturnException() {
        Room room = createRoom();

        when(roomRepository.existsById(room.getRoomId())).thenReturn(false);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.bookRoom(room.getRoomId());
        });

        assertNotNull(exception.getMessage());

        verify(roomRepository, times(0)).deleteById(room.getRoomId());
    }

    @Test
    void whenBookRoomIfFoundShouldBeReturnRoom() {
        Room room = createRoom();

        when(roomRepository.existsById(room.getRoomId())).thenReturn(true);

        when(roomRepository.getById(room.getRoomId())).thenReturn(room);

        when(roomRepository.save(room)).thenReturn(room);

        Room actual = roomService.bookRoom(room.getRoomId());

        assertEquals(room, actual);

        verify(roomRepository).save(room);
    }

    @Test
    void whenAddRoomShouldBeReturnRoom() {
        Room room = createRoom();
        when(roomRepository.save(room)).thenReturn(room);

        Room actual = roomService.addRoom(room);

        assertEquals(room, actual);

        verify(roomRepository).save(room);
    }

    @Test
    void whenShowAllRoofIfStatusFalseShouldBeReturnRoomFree() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(createRoom());
        Long hospitalId = 1L;

        when(roomRepository.findAllByBookingStatusFalseAndHospitalHospitalId(hospitalId)).thenReturn(rooms);

        List<Room> actual = roomService.showAllRoomFilterStatus("false", hospitalId);

        assertEquals(rooms, actual);

        verify(roomRepository, times(1))
                .findAllByBookingStatusFalseAndHospitalHospitalId(hospitalId);
    }

    @Test
    void whenShowAllRoofIfStatusTrueShouldBeReturnRoomFree() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(createRoom());
        Long hospitalId = 1L;

        when(roomRepository.findAllByBookingStatusTrueAndHospitalHospitalId(hospitalId)).thenReturn(rooms);

        List<Room> actual = roomService.showAllRoomFilterStatus("true", hospitalId);

        assertEquals(rooms, actual);

        verify(roomRepository, times(1))
                .findAllByBookingStatusTrueAndHospitalHospitalId(hospitalId);
    }

    @Test
    void whenShowAllRoofIfStatusAllShouldBeReturnRoomFree() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(createRoom());
        Long hospitalId = 1L;

        when(roomRepository.findAllByHospitalHospitalId(hospitalId)).thenReturn(rooms);

        List<Room> actual = roomService.showAllRoomFilterStatus("all", hospitalId);

        assertEquals(rooms, actual);

        verify(roomRepository, times(1))
                .findAllByHospitalHospitalId(hospitalId);
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