package com.hospitalManagement.service.impl;

import com.hospitalManagement.entity.Hospital;
import com.hospitalManagement.exception_handling.IdNullException;
import com.hospitalManagement.exception_handling.NotFoundException;
import com.hospitalManagement.repository.HospitalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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

        Hospital actual = hospitalService.addHospital(hospital);

        assertEquals(hospital, actual);
    }

    @Test
    void whenDelete() {
        Long id = 1L;

        doNothing().when(hospitalRepository).deleteById(id);

        hospitalService.deleteHospital(id);

        verify(hospitalRepository, times(1)).deleteById(id);
    }

//    @Test
//    void whenGetHospitalIfFoundShouldBeReturnHospital() {
//        Long id = 1L;
//        Hospital hospital = creteHospital();
//
//        when(hospitalRepository.existsById(id)).thenReturn(true);
//        when(hospitalRepository.getById(id)).thenReturn(mockHospital);
//        Hospital hospital = hospitalService.getHospitalById(id);
//        assertEquals(mockHospital, hospital);
//    }

    @Test
    void WhenEditHospitalIfIdNullShouldBeReturnException() {
        Long id = null;
        Hospital mockHospital = new Hospital();
        mockHospital.setHospitalId(id);
        mockHospital.setAddress("str. Pushkinska 54");
        mockHospital.setDescription("The best hospital");
        mockHospital.setEmail("hospital@gmail.com");
        mockHospital.setName("Hospital of region");
        mockHospital.setPhone("+380956332496");

        Exception exception = assertThrows(IdNullException.class, () -> {
            hospitalService.editHospital(mockHospital);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void WhenEditHospitalIfNotFoundReturnException() {
        Long id = 1L;
        Hospital mockHospital = new Hospital();
        mockHospital.setHospitalId(id);
        mockHospital.setAddress("str. Pushkinska 54");
        mockHospital.setDescription("The best hospital");
        mockHospital.setEmail("hospital@gmail.com");
        mockHospital.setName("Hospital of region");
        mockHospital.setPhone("+380956332496");

        when(hospitalRepository.existsById(id)).thenReturn(false);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            hospitalService.editHospital(mockHospital);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void WhenEditHospitalIfFoundReturnHospital() {
        Long id = 1L;
        Hospital mockHospital = new Hospital();
        mockHospital.setHospitalId(id);
        mockHospital.setAddress("str. Pushkinska 54");
        mockHospital.setDescription("The best hospital");
        mockHospital.setEmail("hospital@gmail.com");
        mockHospital.setName("Hospital of region");
        mockHospital.setPhone("+380956332496");

        when(hospitalRepository.existsById(id)).thenReturn(true);
        when(hospitalRepository.saveAndFlush(mockHospital)).thenReturn(mockHospital);
        Hospital actual = hospitalService.editHospital(mockHospital);
        assertEquals(mockHospital, actual);
        verify(hospitalRepository).saveAndFlush(mockHospital);
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

        assertEquals(hospitals, hospitalService.getAllHospitals());
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