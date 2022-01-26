package com.myMicroservice.hospitalManagement.HospitalManagement.controller;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;
import com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling.NoSuchDataException;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.HospitalService;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiControllerAdmin {

    private final HospitalService hospitalService;
    private final RoomService roomService;

    @GetMapping ("/hospitals")
    @ResponseStatus(HttpStatus.OK)
    public List<Hospital> showAllHospitals() {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        return hospitals;
    }

    @DeleteMapping("/hospitals/{hospital_id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHospitalById(@PathVariable("hospital_id") Long hospital_id) {
        hospitalService.deleteHospital(hospital_id);
        return  "Hospital with id = " + hospital_id + " deleted";
    }

    @GetMapping("/hospitals/{hospital_id}")
    @ResponseStatus(HttpStatus.OK)
    public Hospital showHospitalById(@PathVariable("hospital_id") Long hospital_id) {
        Hospital hospital = hospitalService.getHospitalById(hospital_id);
        return hospital;
    }

//    @GetMapping("/hospitals/{name}")
//    public ResponseEntity<Hospital> showHospitalByName(@PathVariable("name") String name){
//        Hospital hospital = hospitalService.getHospitalByName(name);
//        return new ResponseEntity<>(hospital, HttpStatus.OK);
//    }

    @GetMapping("/hospitals/{hospital_id}/rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<Room> showAllRoomsByHospitalId(@PathVariable("hospital_id") Long hospital_id) {
        List<Room> rooms = roomService.getAllRoomsByHospitalId(hospital_id);
        return rooms;
    }

    @DeleteMapping("/hospitals/rooms/{room_id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteRoomFromHospitalById(@PathVariable("room_id") Long room_id) {
        roomService.deleteRoom(room_id);
        return "Room with id = " + room_id + " deleted";
    }

    @GetMapping("/hospitals/rooms/{room_id}")
    @ResponseStatus(HttpStatus.OK)
    public Room showRoomById(@PathVariable("room_id") Long room_id) {
        Room room = roomService.getRoomById(room_id);
        return room;
    }

    @PostMapping(value = "/hospitals/rooms")
    @ResponseStatus(HttpStatus.OK)
    public Room addRoom(@RequestBody Room room) {
        roomService.addRoom(room);
        return room;
    }

    @PatchMapping("/hospitals/rooms")
    @ResponseStatus(HttpStatus.OK)
    public Room changeRoom(@RequestBody Room room) {
        roomService.addRoom(room);
        return room;
    }

    @PostMapping("/hospitals")
    @ResponseStatus(HttpStatus.OK)
    public Hospital addHospital(@Valid @RequestBody Hospital hospital) {
        hospitalService.addHospital(hospital);
        return hospital;
    }

    @PatchMapping("/hospitals")
    @ResponseStatus(HttpStatus.OK)
    public Hospital changeHospital(@RequestBody Hospital hospital) {
        hospitalService.editHospital(hospital);
        return hospital;
    }
}
