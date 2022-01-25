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
@Validated
public class ApiControllerAdmin {

    private final HospitalService hospitalService;
    private final RoomService roomService;

    @GetMapping ("/hospitals")
    public ResponseEntity<List<Hospital>> showAllHospitals() {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    @DeleteMapping("/hospitals/{hospital_id}")
    public ResponseEntity<String> deleteHospitalById(@PathVariable("hospital_id") Long hospital_id) {
//        System.out.println("enter");
//        Hospital hospital = hospitalService.getHospitalById(hospital_id);
//        System.out.print("hospital   " + hospital);
//        if (hospital == null) {
//            System.out.println("EXCEPTION");
////            throw new NoSuchDataException("There is no Hospital with id = " + hospital_id + " in DB");
//        }

        hospitalService.deleteHospital(hospital_id);
        return new ResponseEntity<>("Hospital with id = " + hospital_id + " deleted", HttpStatus.OK);
    }

    @GetMapping("/hospitals/{hospital_id}")
    public ResponseEntity<Hospital> showHospitalById(@PathVariable("hospital_id") Long hospital_id) {
        Hospital hospital = hospitalService.getHospitalById(hospital_id);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

//    @GetMapping("/hospitals/{name}")
//    public ResponseEntity<Hospital> showHospitalByName(@PathVariable("name") String name){
//        Hospital hospital = hospitalService.getHospitalByName(name);
//        return new ResponseEntity<>(hospital, HttpStatus.OK);
//    }

    @GetMapping("/hospitals/{hospital_id}/rooms")
    public ResponseEntity<List<Room>> showAllRoomsByHospitalId(@PathVariable("hospital_id") Long hospital_id) {
        List<Room> rooms = roomService.getAllRoomsByHospitalId(hospital_id);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

//    I have a question!!!
//    @DeleteMapping("/hospitals/{hospital_id}/rooms/{room_id}")
    @DeleteMapping("/hospitals/rooms/{room_id}")
    public ResponseEntity<String> deleteRoomFromHospitalById(@PathVariable("room_id") Long room_id) {
        roomService.deleteRoom(room_id);
        return new ResponseEntity<>("Room with id = " + room_id + " deleted", HttpStatus.OK);
    }

//    I have a question!!!
//    @GetMapping(value = "/hospitals/{name_hospital}/rooms/{room_id}")
    @GetMapping("/hospitals/rooms/{room_id}")
    public ResponseEntity<Room> showRoomById(@PathVariable("room_id") Long room_id) {
        Room room = roomService.getRoomById(room_id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

//    I have a question!!!
//    @PostMapping(value = "/hospitals/{hospital_id}/rooms")
    @PostMapping(value = "/hospitals/rooms")
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        roomService.addRoom(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

//    I have a question!!!
//    @PatchMapping("/hospitals/{gospital_id}/rooms")
    @PatchMapping("/hospitals/rooms")
    public ResponseEntity<Room> changeRoom(@RequestBody Room room) {
        roomService.addRoom(room);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PostMapping("/hospitals")
    public ResponseEntity<Hospital> addHospital(@Valid @RequestBody Hospital hospital) {
//        Hospital testHospital = new Hospital("", "", "", " ", "");
        hospitalService.addHospital(hospital);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @PatchMapping("/hospitals")
    public ResponseEntity<Hospital> changeHospital(@RequestBody Hospital hospital) {
        hospitalService.addHospital(hospital);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }
}
