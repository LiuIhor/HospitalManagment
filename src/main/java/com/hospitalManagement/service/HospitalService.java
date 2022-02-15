package com.hospitalManagement.service;

import com.hospitalManagement.dto.HospitalDTO;
import com.hospitalManagement.entity.Hospital;
import com.hospitalManagement.exception_handling.IdNullException;
import com.hospitalManagement.exception_handling.NotFoundException;

import java.util.List;

/**
 * HospitalService - Service with business logic for Hospitals
 */
public interface HospitalService {

    /**
     * addHospital - method to add hospital in the DB
     *
     * @param hospital - this is the Hospital object that will be recorded in the database
     * @return Saved hospital
     */
    HospitalDTO addHospital(HospitalDTO hospital);

    /**
     * deleteHospital - method to delete by id hospital from the DB
     *
     * @param id - this is the id by which the Hospital object will be deleted
     */
    void deleteHospital(Long id);

    /**
     * getHospitalById - method to get hospital by id from DB
     *
     * @param id - this is the id by which the Hospital object will be received
     * @return Hospital
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    HospitalDTO getHospitalById(Long id);

    /**
     * getHospitalEntityById - method to get hospital by id from DB how Entity
     *
     * @param id - this is the id by which the Hospital object will be received
     * @return Hospital
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    Hospital getHospitalEntityById(Long id);

    /**
     * editHospital - method to edit hospital
     *
     * @param hospital - this is the Hospital object that will be received in the database
     * @return Changed hospital
     * @throws IdNullException   - occurs when id equals null
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    HospitalDTO editHospital(HospitalDTO hospital);

    /**
     * getAllHospitals - method to get all hospitals
     *
     * @return List of hospitals
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    List<HospitalDTO> getAllHospitals();

    /**
     * generateSVG - method to generate map
     *
     * @param hospitalId - this is the id by which the Hospital card will be received
     * @return Array bytes
     */
    byte[] generateSVG(Long hospitalId);
}
