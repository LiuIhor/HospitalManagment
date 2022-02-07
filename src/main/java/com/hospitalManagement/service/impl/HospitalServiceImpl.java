package com.hospitalManagement.service.impl;

import com.hospitalManagement.dto.HospitalDTO;
import com.hospitalManagement.entity.Hospital;
import com.hospitalManagement.exception_handling.IdNullException;
import com.hospitalManagement.exception_handling.NotFoundException;
import com.hospitalManagement.repository.HospitalRepository;
import com.hospitalManagement.service.HospitalService;

import com.hospitalManagement.utils.ConvertHospitalUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * HospitalService - Service with business logic for Hospitals
 */
@Service
@RequiredArgsConstructor
@Transactional
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    /**
     * addHospital - method to add hospital in the DB
     *
     * @param hospital - this is the Hospital object that will be recorded in the database
     */
    @Override
    public HospitalDTO addHospital(HospitalDTO hospital) {
        return ConvertHospitalUtil.convertToDTO(
                hospitalRepository.save(ConvertHospitalUtil.convertToEntity(hospital)));
    }

    /**
     * deleteHospital - method to delete by id hospital from the DB
     *
     * @param id - this is the id by which the Hospital object will be deleted
     */
    @Override
    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }

    /**
     * getHospitalById - method to get hospital by id from DB
     *
     * @param id - this is the id by which the Hospital object will be received
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    @Override
    public HospitalDTO getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("I don`t found Hospital with id %d", id)));
        return ConvertHospitalUtil.convertToDTO(hospital);
    }

    /**
     * editHospital - method to edit hospital
     *
     * @param hospital - this is the Hospital object that will be received in the database
     * @throws IdNullException   - occurs when id equals null
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    @Override
    public HospitalDTO editHospital(HospitalDTO hospital) {
       if (!hospitalRepository.existsById(hospital.getHospitalId())) {
            throw new NotFoundException(String.format("You can`t edit Hospital. " +
                    "Because hospital with id %d not founded!", hospital.getHospitalId()));
        }
        return ConvertHospitalUtil.convertToDTO(
                hospitalRepository.save(ConvertHospitalUtil.convertToEntity(hospital)));
    }

    /**
     * getAllHospitals - method to get all hospitals
     *
     * @throws NotFoundException - occurs when objects are not found in the database
     * @return
     */
    @Override
    public List<HospitalDTO> getAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        if (hospitals.isEmpty()) {
            throw new NotFoundException("Table hospitals in BD is empty");
        }
        return hospitals.stream().map(ConvertHospitalUtil::convertToDTO).collect(Collectors.toList());
    }
}
