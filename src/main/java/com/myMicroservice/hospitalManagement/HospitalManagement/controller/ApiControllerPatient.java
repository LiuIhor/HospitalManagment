package com.myMicroservice.hospitalManagement.HospitalManagement.controller;

import com.myMicroservice.hospitalManagement.HospitalManagement.service.HospitalService;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.RoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "Endpoints for work with map",
        tags = {"Patient"})
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiControllerPatient {

    private final HospitalService hospitalService;

    private final RoomService roomService;

    //TO-DO
    @GetMapping("/hospitals/{hospital_id}/map")
    @ApiOperation(value = "Returns data for map of hospital as graph",
            notes = "Just returns data for map of hospital as graph"/*,
            response = Room.class*/)
    public ResponseEntity<Void> showMapAsGraph(@ApiParam(name = "The parameter is needed to get data hospital by id",
            value = "Id of the hospital to be obtained. Cannot be empty.",
            example = "1",
            required = true) @PathVariable("hospital_id") Long hospital_id) {
        return null;
    }

    //TO-DO
    @RequestMapping(value = "/hospitals/{hospital_id}/map-svg",
            method = RequestMethod.GET)
    @ApiOperation(value = "Generate actual map as svg picture",
            notes = "Just generate actual map as svg picture\""/*,
            response = Room.class*/)
    public ResponseEntity<Void> generateSvgMap(@ApiParam(name = "The parameter is needed to generate actual map" +
            " of hospital by id",
            value = "Id of the hospital to be obtained. Cannot be empty.",
            example = "1",
            required = true) @PathVariable("hospital_id") Long hospital_id) {
        return null;
    }
}
