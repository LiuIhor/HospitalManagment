package com.myMicroservice.hospitalManagement.HospitalManagement.controller;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.HospitalService;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiControllerDoctor {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/hospitals/{hospital_id}/free-rooms")
    public ResponseEntity<List<Room>> showFreeRooms(@PathVariable("hospital_id") Long hospital_id) {
        List<Room> freeRooms = roomService.showFreeRooms(hospital_id);
        return new ResponseEntity<>(freeRooms, HttpStatus.OK);
    }

    //TO-DO
    @PostMapping("/hospitals/{hospital_id}/rooms/book")
    public ResponseEntity<Void> bookRoom(@PathVariable("hospital_id") Long hospital_id) {

        return null;
    }
}
