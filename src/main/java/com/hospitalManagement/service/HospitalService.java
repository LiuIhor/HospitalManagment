package com.hospitalManagement.service;

import com.hospitalManagement.entity.Hospital;

import java.util.List;

public interface HospitalService {

    Hospital addHospital(Hospital hospital);

    void deleteHospital(Long id);

    Hospital getHospitalById(Long id);

    Hospital editHospital(Hospital hospital);

    List<Hospital> getAllHospitals();
}
