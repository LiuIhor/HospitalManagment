package com.myMicroservice.hospitalManagement.HospitalManagement.service.impl;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling.NoSuchDataException;
import com.myMicroservice.hospitalManagement.HospitalManagement.repository.HospitalRepository;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public Hospital addHospital(Hospital hospital) {
        Hospital savedHospital = hospitalRepository.saveAndFlush(hospital);
        return savedHospital;
    }

    @Override
    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }

    @Override
    public Hospital getHospitalByName(String name) {
        return hospitalRepository.findByName(name);
    }

    @Override
    public Hospital getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.getById(id);
//        System.out.println(hospital.getId());
//        if (hospital.getId() == null) {
//            System.out.println("null");
//        }
//        System.out.println("hospital " + hospital.getId());
        return hospital;
    }

    @Override
    public Hospital editHospital(Hospital hospital) {
        return hospitalRepository.saveAndFlush(hospital);
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }


}
