package com.hospitalManagement.service.impl;

import com.hospitalManagement.dto.HospitalDTO;
import com.hospitalManagement.entity.Hospital;
import com.hospitalManagement.entity.Room;
import com.hospitalManagement.exception_handling.IdNullException;
import com.hospitalManagement.exception_handling.NotFoundException;
import com.hospitalManagement.repository.HospitalRepository;
import com.hospitalManagement.repository.RoomRepository;
import com.hospitalManagement.service.HospitalService;
import com.hospitalManagement.utils.modelMapper.ConvertHospitalUtil;
import com.hospitalManagement.utils.svg.SvgUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    private final RoomRepository roomRepository;

    /**
     * addHospital - method to add hospital in the DB
     *
     * @param hospital - this is the Hospital object that will be recorded in the database
     * @return Saved hospital
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
     * @return Hospital
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    @Override
    public HospitalDTO getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Hospital with id %d does not exists", id)));
        return ConvertHospitalUtil.convertToDTO(hospital);
    }

    /**
     * getHospitalEntityById - method to get hospital by id from DB how Entity
     *
     * @param id - this is the id by which the Hospital object will be received
     * @return Hospital
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    @Override
    public Hospital getHospitalEntityById(Long id) {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Hospital with id %d does not exists!", id)));
    }

    /**
     * editHospital - method to edit hospital
     *
     * @param hospital - this is the Hospital object that will be received in the database
     * @return Changed hospital
     * @throws IdNullException   - occurs when id equals null
     * @throws NotFoundException - occurs when an object is not found in the database
     */
    @Override
    public HospitalDTO editHospital(HospitalDTO hospital) {
        if (!hospitalRepository.existsById(hospital.getHospitalId())) {
            throw new NotFoundException(String.format("Hospital with id %d does not exists!", hospital.getHospitalId()));
        }
        return ConvertHospitalUtil.convertToDTO(
                hospitalRepository.save(ConvertHospitalUtil.convertToEntity(hospital)));
    }

    /**
     * getAllHospitals - method to get all hospitals
     *
     * @return List of hospitals
     * @throws NotFoundException - occurs when objects are not found in the database
     */
    @Override
    public List<HospitalDTO> getAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        if (CollectionUtils.isEmpty(hospitals)) {
            throw new NotFoundException("Hospitals do not exist");
        }
        return hospitals.stream().map(ConvertHospitalUtil::convertToDTO).collect(Collectors.toList());
    }

    /**
     * generateSVG - method to generate map
     *
     * @param hospitalId - this is the id by which the Hospital card will be received
     * @return Array bytes
     */
    @Override
    public byte[] generateSVG(Long hospitalId) {
        List<Room> rooms = roomRepository.findAllByHospitalHospitalId(hospitalId);
        byte[] map = null;
        map = SvgUtil.svg(rooms);
        return map;
    }
}
