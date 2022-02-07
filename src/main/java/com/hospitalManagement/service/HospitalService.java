package com.hospitalManagement.service;

import com.hospitalManagement.dto.HospitalDTO;

import java.util.List;

public interface HospitalService {

    HospitalDTO addHospital(HospitalDTO hospital);

    void deleteHospital(Long id);

    HospitalDTO getHospitalById(Long id);

    HospitalDTO editHospital(HospitalDTO hospital);

    List<HospitalDTO> getAllHospitals();
}
