package com.myMicroservice.hospitalManagement.HospitalManagement.service;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HospitalService {
    Hospital addHospital(Hospital hospital);
    void deleteHospital(Long id);
    Hospital getHospitalByName(String name);
    Hospital getHospitalById(Long id);
    Hospital editHospital(Hospital hospital);
    List<Hospital> getAllHospitals();

}
