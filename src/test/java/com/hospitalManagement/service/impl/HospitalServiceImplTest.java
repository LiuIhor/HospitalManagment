package com.hospitalManagement.service.impl;

import com.hospitalManagement.dto.HospitalDTO;
import com.hospitalManagement.entity.Hospital;
import com.hospitalManagement.exception_handling.NotFoundException;
import com.hospitalManagement.repository.HospitalRepository;

import com.hospitalManagement.utils.modelMapper.ConvertHospitalUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HospitalServiceImplTest {

    @Mock
    private HospitalRepository hospitalRepository;

    @InjectMocks
    private HospitalServiceImpl hospitalService;

    @Test
    void whenSaveHospitalShouldReturnHospital() {
        Hospital hospital = creteHospital();

        when(hospitalRepository.save(hospital)).thenReturn(hospital);

        HospitalDTO actual = hospitalService.addHospital(ConvertHospitalUtil.convertToDTO(hospital));

        assertEquals(ConvertHospitalUtil.convertToDTO(hospital), actual);
    }

    @Test
    void whenDelete() {
        Long id = 1L;

        doNothing().when(hospitalRepository).deleteById(id);

        hospitalService.deleteHospital(id);

        verify(hospitalRepository, times(1)).deleteById(id);
    }

    @Test
    void whenGetHospitalIfFoundShouldBeReturnHospital() {
        Long id = 1L;
        Hospital hospital = creteHospital();

        when(hospitalRepository.findById(id)).thenReturn(Optional.ofNullable(hospital));

        HospitalDTO actual = hospitalService.getHospitalById(id);

        assertEquals(ConvertHospitalUtil.convertToDTO(hospital), actual);

        verify(hospitalRepository, times(1)).findById(id);
    }

    @Test
    void whenGetHospitalIfNotFoundShouldBeReturnException() {
        Long id = 1L;

        Exception exception = assertThrows(NotFoundException.class, () -> {
            hospitalService.getHospitalById(id);
        });

        assertNotNull(exception.getMessage());

        verify(hospitalRepository, times(1)).findById(id);
    }

    @Test
    void WhenEditHospitalIfNotFoundShouldBeReturnException() {
        Hospital hospital = creteHospital();

        Exception exception = assertThrows(NotFoundException.class, () -> {
            hospitalService.editHospital(ConvertHospitalUtil.convertToDTO(hospital));
        });

        assertNotNull(exception.getMessage());

        verify(hospitalRepository, times(0)).save(hospital);
    }

    @Test
    void WhenEditHospitalIfFoundReturnException() {
        Hospital hospital = creteHospital();

        when(hospitalRepository.existsById(hospital.getHospitalId())).thenReturn(true);

        when(hospitalRepository.save(hospital)).thenReturn(hospital);

        HospitalDTO actual = hospitalService.editHospital(ConvertHospitalUtil.convertToDTO(hospital));

        assertEquals(ConvertHospitalUtil.convertToDTO(hospital), actual);

        verify(hospitalRepository, times(1)).save(hospital);
    }

    @Test
    void whenGetAllHospitalsIfListEmptyShouldBeReturnException() {
        List<Hospital> hospitals = new ArrayList<>();

        when(hospitalRepository.findAll()).thenReturn(hospitals);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            hospitalService.getAllHospitals();
        });

        assertNotNull(exception.getMessage());
    }

    @Test
    void whenGetAllHospitalsIfListNotEmptyShouldBeReturnHospitals() {
        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(new Hospital());
        when(hospitalRepository.findAll()).thenReturn(hospitals);
        assertEquals( hospitals.stream().map(ConvertHospitalUtil::convertToDTO).collect(Collectors.toList()),
                hospitalService.getAllHospitals());
        verify(hospitalRepository).findAll();
    }

    Hospital creteHospital() {
        Hospital hospital = new Hospital();
        hospital.setHospitalId(1L);
        hospital.setPhone("+546874337298");
        hospital.setName("Name");
        hospital.setAddress("address");
        hospital.setEmail("email@gmail.com");
        hospital.setHospitalId(1L);
        return hospital;
    }
}