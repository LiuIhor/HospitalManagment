package com.myMicroservice.hospitalManagement.HospitalManagement.controller;

import com.myMicroservice.hospitalManagement.HospitalManagement.service.HospitalService;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiControllerPatient {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private RoomService roomService;

    //TO-DO
    @GetMapping("/hospitals/{hospital_id}/map")
    public ResponseEntity<Void> showMapAsGraph(@PathVariable("hospital_id") Long hospital_id) {
        return null;
    }

    //TO-DO
    @RequestMapping(value = "/hospitals/{hospital_id}/map-svg",
            method = RequestMethod.GET)
    public ResponseEntity<Void> generateSvgMap(@PathVariable("hospital_id") Long hospital_id) {
        return null;
    }
}
