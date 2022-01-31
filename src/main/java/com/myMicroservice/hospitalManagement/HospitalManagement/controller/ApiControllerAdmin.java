package com.myMicroservice.hospitalManagement.HospitalManagement.controller;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.HospitalService;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.RoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@Api(description = "Endpoints for Creating, Retrieving, Updating and Deleting of Hospitals and Rooms.",
        tags = {"Admin"})
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiControllerAdmin {

    private final HospitalService hospitalService;
    private final RoomService roomService;

    @GetMapping("/hospitals")
    @ApiOperation(value = "Finds all hospitals",
            notes = "Just returns all hospitals",
            response = Hospital.class,
            responseContainer = "List")
    @ResponseStatus(HttpStatus.OK)
    public List<Hospital> showAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    @DeleteMapping("/hospitals/{hospital_id}")
    @ApiOperation(value = "Delete hospital by Id",
            notes = "Finds hospital in the db and deletes it",
            response = String.class)
    @ResponseStatus(HttpStatus.OK)
    public String deleteHospitalById(@ApiParam(name = "The parameter is needed to delete hospital by id",
            value = "Id of the hospital to be obtained. Cannot be empty.",
            example = "1",
            required = true) @PathVariable("hospital_id") Long hospital_id) {
        hospitalService.deleteHospital(hospital_id);
        return "Hospital with id = " + hospital_id + " deleted";
    }

    @GetMapping("/hospitals/{hospital_id}")
    @ApiOperation(value = "Finds hospital by id",
            notes = "Just returns hospital by id",
            response = Hospital.class)
    @ResponseStatus(HttpStatus.OK)
    public Hospital showHospitalById(@ApiParam(name = "The parameter is needed to get hospital by id",
            value = "Id of the hospital to be obtained. Cannot be empty.",
            example = "1",
            required = true) @PathVariable("hospital_id") Long hospital_id) {
        return hospitalService.getHospitalById(hospital_id);
    }

    @GetMapping("/hospitals/{hospital_id}/rooms")
    @ApiOperation(value = "Finds all rooms by hospital id",
            notes = "Just returns all rooms by hospital id",
            response = Room.class,
            responseContainer = "List")
    @ResponseStatus(HttpStatus.OK)
    public List<Room> showAllRoomsByHospitalId(@ApiParam(name = "The parameter is needed to get all rooms by  hospital id",
            value = "Id of the hospital to be obtained. Cannot be empty.",
            example = "1",
            required = true) @PathVariable("hospital_id") Long hospital_id) {
        return roomService.getAllRoomsByHospitalId(hospital_id);
    }

    @DeleteMapping("/hospitals/rooms/{room_id}")
    @ApiOperation(value = "Delete room by hospital id",
            notes = "Finds room by hospital id in the db and deletes it",
            response = String.class)
    @ResponseStatus(HttpStatus.OK)
    public String deleteRoomFromHospitalById(@ApiParam(name = "The parameter is needed to delete room by id",
            value = "Id of the hospital to be obtained. Cannot be empty.",
            example = "1",
            required = true)@PathVariable("room_id") Long room_id) {
        roomService.deleteRoom(room_id);
        return "Room with id = " + room_id + " deleted";
    }

    @GetMapping("/hospitals/rooms/{room_id}")
    @ApiOperation(value = "Finds room by id",
            notes = "Just returns rooms by id",
            response = Room.class)
    @ResponseStatus(HttpStatus.OK)
    public Room showRoomById(@ApiParam(name = "The parameter is needed to get room by id",
            value = "Id of the room to be obtained. Cannot be empty.",
            example = "1",
            required = true)@PathVariable("room_id") Long room_id) {

        return roomService.getRoomById(room_id);
    }

    @PostMapping(value = "/hospitals/rooms")
    @ApiOperation(value = "Add new room",
            notes = "Just add room in db",
            response = Room.class)
    @ResponseStatus(HttpStatus.OK)
    public Room addRoom(@ApiParam(name = "Object room to be written to the DB")
                        @RequestBody Room room) {
        roomService.addRoom(room);
        return room;
    }

    @PatchMapping("/hospitals/rooms")
    @ApiOperation(value = "Changes room",
            notes = "Just changes room",
            response = Room.class)
    @ResponseStatus(HttpStatus.OK)
    public Room changeRoom(@ApiParam(name = "Changed object room to be written to the DB")
                           @Valid @RequestBody Room room) {
        return roomService.editRoom(room);
    }

    @PostMapping("/hospitals")
    @ApiOperation(value = "Add new hospital",
            notes = "Just add new hospital in db",
            response = Hospital.class)
    @ResponseStatus(HttpStatus.OK)
    public Hospital addHospital(@ApiParam(name = "Object hospital to be written to the DB")
                                @Valid @RequestBody Hospital hospital) {
        hospitalService.addHospital(hospital);
        return hospital;
    }

    @PatchMapping("/hospitals")
    @ApiOperation(value = "Changes hospital",
            notes = "Just changes hospital",
            response = Hospital.class)
    @ResponseStatus(HttpStatus.OK)
    public Hospital changeHospital(@ApiParam(name = "Changed object hospital to be written to the DB")
                                   @Valid @RequestBody Hospital hospital) {
        hospitalService.editHospital(hospital);
        return hospital;
    }
}
