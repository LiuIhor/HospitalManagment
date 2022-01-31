package com.myMicroservice.hospitalManagement.HospitalManagement.service.impl;

import com.myMicroservice.hospitalManagement.HospitalManagement.entity.Hospital;
import com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling.IdNullException;
import com.myMicroservice.hospitalManagement.HospitalManagement.exception_handling.NoSuchDataException;
import com.myMicroservice.hospitalManagement.HospitalManagement.repository.HospitalRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class HospitalServiceImplTest {

    @Mock
    private HospitalRepository hospitalRepository;

    @InjectMocks
    private HospitalServiceImpl hospitalService;

//    @BeforeEach
//    public void setUp() {
//        Mo
//    }

    @Test
    void whenSaveHospitalShouldReturnHospital() {
        Hospital mockHospital = new Hospital();
        mockHospital.setHospital_id(1L);
        mockHospital.setAddress("str. Pushkinska 54");
        mockHospital.setDescription("The best hospital");
        mockHospital.setEmail("hospital@gmail.com");
        mockHospital.setName("Hospital of region");
        mockHospital.setPhone("+380956332496");

        when(hospitalRepository.saveAndFlush(mockHospital)).thenReturn(mockHospital);
        assertEquals(mockHospital, hospitalService.addHospital(mockHospital));
    }

    @Test
    void whenDeleteIfFoundShouldBeReturnTrue() {
//        Hospital mockHospital = mock(Hospital.class);
        Long id = 1L;
        Hospital mockHospital = new Hospital();
        mockHospital.setHospital_id(id);
        mockHospital.setAddress("str. Pushkinska 54");
        mockHospital.setDescription("The best hospital");
        mockHospital.setEmail("hospital@gmail.com");
        mockHospital.setName("Hospital of region");
        mockHospital.setPhone("+380956332496");

        when(hospitalRepository.existsById(id)).thenReturn(true);
        boolean result = hospitalService.deleteHospital(id);
        assertTrue(result);
        verify(hospitalRepository).deleteById(id);
    }

    @Test
    void whenDeleteIfNotFoundShouldBeReturnException() {
//        Hospital mockHospital = mock(Hospital.class);
        Long id = 1L;
        Hospital mockHospital = new Hospital();
        mockHospital.setHospital_id(id);
        mockHospital.setAddress("str. Pushkinska 54");
        mockHospital.setDescription("The best hospital");
        mockHospital.setEmail("hospital@gmail.com");
        mockHospital.setName("Hospital of region");
        mockHospital.setPhone("+380956332496");
        when(hospitalRepository.existsById(id)).thenReturn(false);
        Exception exception = assertThrows(NoSuchDataException.class, () -> {
            hospitalService.deleteHospital(id);
        });
        assertNotNull(exception.getMessage());
        verify(hospitalRepository, times(0)).deleteById(id);
    }

    @Test
    void whenGetHospitalIfFoundShouldBeReturnHospital() {
        Long id = 1L;
        Hospital mockHospital = new Hospital();
        mockHospital.setHospital_id(id);
        mockHospital.setAddress("str. Pushkinska 54");
        mockHospital.setDescription("The best hospital");
        mockHospital.setEmail("hospital@gmail.com");
        mockHospital.setName("Hospital of region");
        mockHospital.setPhone("+380956332496");
        when(hospitalRepository.existsById(id)).thenReturn(true);
        when(hospitalRepository.getById(id)).thenReturn(mockHospital);
        Hospital hospital = hospitalService.getHospitalById(id);
        assertEquals(mockHospital, hospital);
    }

    @Test
    void whenDeleteHospitalIfNotFoundShouldBeReturnException() {
        Long id = 1L;
        Hospital mockHospital = new Hospital();
        mockHospital.setHospital_id(id);
        mockHospital.setAddress("str. Pushkinska 54");
        mockHospital.setDescription("The best hospital");
        mockHospital.setEmail("hospital@gmail.com");
        mockHospital.setName("Hospital of region");
        mockHospital.setPhone("+380956332496");
        when(hospitalRepository.existsById(id)).thenReturn(false);
        Exception exception = assertThrows(NoSuchDataException.class, () -> {
            hospitalService.getHospitalById(id);
        });
        assertNotNull(exception.getMessage());
        verify(hospitalRepository, times(0)).deleteById(id);
    }

    @Test
    void WhenEditHospitalIfIdNullShouldBeReturnException() {
        Long id = null;
        Hospital mockHospital = new Hospital();
        mockHospital.setHospital_id(id);
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
        mockHospital.setHospital_id(id);
        mockHospital.setAddress("str. Pushkinska 54");
        mockHospital.setDescription("The best hospital");
        mockHospital.setEmail("hospital@gmail.com");
        mockHospital.setName("Hospital of region");
        mockHospital.setPhone("+380956332496");

        when(hospitalRepository.existsById(id)).thenReturn(false);
        Exception exception = assertThrows(NoSuchDataException.class, () -> {
            hospitalService.editHospital(mockHospital);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void WhenEditHospitalIfFoundReturnHospital() {
        Long id = 1L;
        Hospital mockHospital = new Hospital();
        mockHospital.setHospital_id(id);
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

        Exception exception = assertThrows(NoSuchDataException.class, () -> {
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
}