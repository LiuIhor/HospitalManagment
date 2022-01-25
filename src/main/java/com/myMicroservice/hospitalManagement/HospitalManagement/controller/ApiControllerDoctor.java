package com.myMicroservice.hospitalManagement.HospitalManagement.controller;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;
import com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling.NoSuchDataException;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.HospitalService;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiControllerDoctor {

    private final HospitalService hospitalService;

    private final RoomService roomService;

    @GetMapping("/hospitals/{hospital_id}/free-rooms")
    public ResponseEntity<List<Room>> showFreeRooms(@PathVariable("hospital_id") Long hospital_id) {
        List<Room> freeRooms = roomService.showFreeRooms(hospital_id);
        return new ResponseEntity<>(freeRooms, HttpStatus.OK);
    }

    //TO-DO
    @PostMapping("/hospitals/rooms/{room_id}/book")
    public ResponseEntity<Room> bookRoom(@PathVariable("room_id") Long room_id) {
        Room bookedRoom = roomService.bookRoom(room_id);
        return new ResponseEntity<>(bookedRoom, HttpStatus.OK);
    }
}
