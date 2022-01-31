package com.myMicroservice.hospitalManagement.HospitalManagement.service.impl;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling.IdNullException;
import com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling.NoSuchDataException;
import com.myMicroservice.hospitalManagement.HospitalManagement.repository.HospitalRepository;
import com.myMicroservice.hospitalManagement.HospitalManagement.service.HospitalService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    @Override
    public Hospital addHospital(Hospital hospital) {
        return hospitalRepository.saveAndFlush(hospital);
    }

    @Override
    public boolean deleteHospital(Long id) {
        if (hospitalRepository.existsById(id)) {
            System.out.println("Hospital with id " + id + " founded!");
            hospitalRepository.deleteById(id);
        } else {
            System.out.println("You can`t delete this hospital. Because hospital with id " + id + " not founded!");
            throw new NoSuchDataException("You can`t delete this hospital. Because hospital with id " + id + " not founded!");
        }
        System.out.println("Hospital Deleted");
        return true;
    }

    @Override
    public Hospital getHospitalById(Long id) {
        Hospital hospital;
        if (hospitalRepository.existsById(id)) {
            System.out.println("Hospital with id " + id + " founded!");
            hospital = hospitalRepository.getById(id);
        } else {
            System.out.println("Hospital with id " + id + " not founded!");
            throw new NoSuchDataException("Hospital with id " + id + " not founded!");
//            return null;
        }
        return hospital;
    }

    @Override
    public Hospital editHospital(Hospital hospital) {
        System.out.println(hospital.getHospital_id());
        if (hospital.getHospital_id() == null) {
            System.out.println("You lol");
            throw new IdNullException("The given id must not be null!");
        } else if (!hospitalRepository.existsById(hospital.getHospital_id())) {
            System.out.println("You can`t edit Hospital. Because hospital with id "
                    + hospital.getHospital_id() + " not founded!");
            throw new NoSuchDataException("You can`t edit Hospital. Because hospital with id "
                    + hospital.getHospital_id() + " not founded!");
        }
        System.out.println("Hospital with id = " + hospital.getHospital_id() + "edited");
        return hospitalRepository.saveAndFlush(hospital);
    }

    @Override
    public List<Hospital> getAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        if (hospitals.isEmpty()) {
            throw new NoSuchDataException("table hospitals in BD is empty");
        }
        return hospitals;
    }
}
