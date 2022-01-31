package com.myMicroservice.hospitalManagement.HospitalManagement.controller;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Room;

import com.myMicroservice.hospitalManagement.HospitalManagement.service.HospitalService;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.RoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Endpoints for search and book Rooms",
        tags = {"Doctor"})
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiControllerDoctor {

    private final RoomService roomService;

    @GetMapping("/hospitals/{hospital_id}/free-rooms")
    @ApiOperation(value = "Finds free rooms by hospital id",
            notes = "Just finds rooms by hospital id",
            response = List.class)
    @ResponseStatus(HttpStatus.OK)
    public List<Room> showFreeRooms(@ApiParam(name = "The parameter is needed to get free room in hospital by hospital id",
            value = "Id of the hospital to be obtained. Cannot be empty.",
            example = "1",
            required = true) @PathVariable("hospital_id") Long hospital_id) {
        return roomService.showFreeRooms(hospital_id);
    }

    @PostMapping("/hospitals/rooms/{room_id}/book")
    @ApiOperation(value = "Books room be id",
            notes = "Just books room by id",
            response = Room.class)
    @ResponseStatus(HttpStatus.OK)
    public Room bookRoom(@ApiParam(name = "The parameter is needed to book room in hospital by id",
            value = "Id of the hospital to be obtained. Cannot be empty.",
            example = "1",
            required = true) @PathVariable("room_id") Long room_id) {
        return roomService.bookRoom(room_id);
    }
}
