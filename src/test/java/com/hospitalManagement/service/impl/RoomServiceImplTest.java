package com.hospitalManagement.service.impl;

import com.hospitalManagement.dto.RoomDTO;
import com.hospitalManagement.entity.Hospital;
import com.hospitalManagement.entity.Room;
import com.hospitalManagement.exception_handling.NotFoundException;
import com.hospitalManagement.repository.RoomRepository;
import com.hospitalManagement.utils.modelMapper.ConvertRoomUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        assertEquals(ConvertRoomUtil.convertToDTO(room), roomService.addRoom(ConvertRoomUtil.convertToDTO(room)));

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

        RoomDTO actual = roomService.getRoomById(room.getRoomId());

        assertEquals(ConvertRoomUtil.convertToDTO(room), actual);

        verify(roomRepository, times(1)).findById(room.getRoomId());
    }

    @Test
    void whenEditRoomIfNotFoundReturnException() {
        Room room = createRoom();

        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.editRoom(ConvertRoomUtil.convertToDTO(room));
        });

        assertNotNull(exception.getMessage());

        verify(roomRepository, times(0)).save(room);
    }

    @Test
    void WhenEditRoomIfFoundReturnHospital() {
        Room room = createRoom();

        when(roomRepository.existsById(room.getRoomId())).thenReturn(true);

        when(roomRepository.save(room)).thenReturn(room);

        RoomDTO actual = roomService.editRoom(ConvertRoomUtil.convertToDTO(room));

        assertEquals(ConvertRoomUtil.convertToDTO(room), actual);

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
    void whenShowNotFreeRoomsIfListEmptyShouldBeReturnException() {
        List<Room> rooms = new ArrayList<>();
        Long hospitalId = 1L;

        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.showNotFreeRooms(hospitalId);
        });

        assertNotNull(exception.getMessage());
    }

    @Test
    void whenShowFreeRoomsIfListNotEmptyShouldBeReturnRooms() {
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

        verify(roomRepository, times(0)).save(room);
    }


    @Test
    void whenBookRoomIfFoundShouldBeReturnRoom() {
        Room room = createRoom();

        when(roomRepository.existsById(room.getRoomId())).thenReturn(true);

        when(roomRepository.getById(room.getRoomId())).thenReturn(room);

        when(roomRepository.save(room)).thenReturn(room);

        RoomDTO actual = roomService.bookRoom(room.getRoomId());

        assertEquals(ConvertRoomUtil.convertToDTO(room), actual);

        verify(roomRepository).save(room);
    }

    @Test
    void whenUnBookRoomIfNotFoundShouldBeReturnException() {
        Room room = createRoom();

        when(roomRepository.existsById(room.getRoomId())).thenReturn(false);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            roomService.unBookRoom(room.getRoomId());
        });

        assertNotNull(exception.getMessage());

        verify(roomRepository, times(0)).save(room);
    }


    @Test
    void whenUnBookRoomIfFoundShouldBeReturnRoom() {
        Room room = createRoom();

        when(roomRepository.existsById(room.getRoomId())).thenReturn(true);

        when(roomRepository.getById(room.getRoomId())).thenReturn(room);

        when(roomRepository.save(room)).thenReturn(room);

        RoomDTO actual = roomService.unBookRoom(room.getRoomId());

        assertEquals(ConvertRoomUtil.convertToDTO(room), actual);

        verify(roomRepository).save(room);
    }

    @Test
    void whenAddRoomShouldBeReturnRoom() {
        Room room = createRoom();
        when(roomRepository.save(room)).thenReturn(room);

        RoomDTO actual = roomService.addRoom(ConvertRoomUtil.convertToDTO(room));

        assertEquals(ConvertRoomUtil.convertToDTO(room), actual);

        verify(roomRepository).save(room);
    }

    @Test
    void whenShowAllRoofIfStatusFalseShouldBeReturnRoomFree() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(createRoom());
        Long hospitalId = 1L;

        when(roomRepository.findAllByBookingStatusFalseAndHospitalHospitalId(hospitalId)).thenReturn(rooms);

        List<RoomDTO> actual = roomService.showAllRoomFilterStatus("false", hospitalId);

        assertEquals(rooms.stream().map(ConvertRoomUtil::convertToDTO).collect(Collectors.toList()), actual);

        verify(roomRepository, times(1))
                .findAllByBookingStatusFalseAndHospitalHospitalId(hospitalId);
    }

    @Test
    void whenShowAllRoofIfStatusTrueShouldBeReturnRoomFree() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(createRoom());
        Long hospitalId = 1L;

        when(roomRepository.findAllByBookingStatusTrueAndHospitalHospitalId(hospitalId)).thenReturn(rooms);

        List<RoomDTO> actual = roomService.showAllRoomFilterStatus("true", hospitalId);

        assertEquals(rooms.stream().map(ConvertRoomUtil::convertToDTO).collect(Collectors.toList()), actual);

        verify(roomRepository, times(1))
                .findAllByBookingStatusTrueAndHospitalHospitalId(hospitalId);
    }

    @Test
    void whenShowAllRoofIfStatusAllShouldBeReturnRoomBusy() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(createRoom());
        Long hospitalId = 1L;

        when(roomRepository.findAllByHospitalHospitalId(hospitalId)).thenReturn(rooms);

        List<RoomDTO> actual = roomService.showAllRoomFilterStatus("all", hospitalId);

        assertEquals(rooms.stream().map(ConvertRoomUtil::convertToDTO).collect(Collectors.toList()), actual);

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