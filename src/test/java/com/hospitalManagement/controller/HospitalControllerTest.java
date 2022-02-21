package com.hospitalManagement.controller;

import com.hospitalManagement.dto.HospitalDTO;
import com.hospitalManagement.entity.Hospital;
import com.hospitalManagement.entity.Room;
import com.hospitalManagement.entity.enums.Type;
import com.hospitalManagement.service.HospitalService;
import com.hospitalManagement.utils.modelMapper.ConvertHospitalUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HospitalControllerTest {

    @Mock
    private HospitalService hospitalService;

    @InjectMocks
    private HospitalController hospitalController;

    @Test
    void showAllHospitals() {
        Hospital hospital = creteHospital();

        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(hospital);

        when(hospitalService.getAllHospitals()).thenReturn(hospitals.stream()
                .map(ConvertHospitalUtil::convertToDTO)
                .collect(Collectors.toList()));

        List<Hospital> actual = hospitalController.showAllHospitals().stream()
                .map(ConvertHospitalUtil::convertToEntity).collect(Collectors.toList());

        assertEquals(hospitals, actual);

        verify(hospitalService, times(1)).getAllHospitals();
    }

    @Test
    void deleteHospitalById() {
        Long hospital_id = 1L;

        hospitalController.deleteHospitalById(hospital_id);

        verify(hospitalService, times(1)).deleteHospital(hospital_id);
    }

    @Test
    void showHospitalById() {
        Hospital hospital = creteHospital();

        when(hospitalService.getHospitalById(hospital.getHospitalId()))
                .thenReturn(ConvertHospitalUtil.convertToDTO(hospital));

        Hospital actual = ConvertHospitalUtil.convertToEntity(
                hospitalController.showHospitalById(hospital.getHospitalId()));

        assertEquals(hospital, actual);

        verify(hospitalService).getHospitalById(hospital.getHospitalId());
    }

    @Test
    void addHospital() {
        Hospital hospital = creteHospital();

        when(hospitalService.addHospital(ConvertHospitalUtil.convertToDTO(hospital)))
                .thenReturn(ConvertHospitalUtil.convertToDTO(hospital));

        HospitalDTO actual = hospitalController.addHospital(ConvertHospitalUtil.convertToDTO(hospital));

        assertEquals(ConvertHospitalUtil.convertToDTO(hospital), actual);

        verify(hospitalService, times(1)).addHospital(ConvertHospitalUtil.convertToDTO(hospital));
    }

    @Test
    void changeHospital() {
        Hospital hospital = creteHospital();

        when(hospitalService.editHospital(ConvertHospitalUtil.convertToDTO(hospital)))
                .thenReturn(ConvertHospitalUtil.convertToDTO(hospital));

        Long hospitalId = 1L;

        HospitalDTO actual = hospitalController.changeHospital(hospitalId, ConvertHospitalUtil.convertToDTO(hospital));

        assertEquals(ConvertHospitalUtil.convertToDTO(hospital), actual);

        verify(hospitalService, times(1))
                .editHospital(ConvertHospitalUtil.convertToDTO(hospital));
    }

    @Test
    void showMapAsGraph() {
        Hospital hospital = creteHospital();
        hospital.setRooms(createRooms());

        when(hospitalService.getHospitalEntityById(1L)).thenReturn(hospital);

        Hospital actual = hospitalController.showMapAsGraph(1L);

        assertEquals(hospital, actual);

        verify(hospitalService, times(1))
                .getHospitalEntityById(1L);
    }

    @Test
    void showMap() {
        when(hospitalService.generateSVG(1L)).thenReturn(new byte[0]);

        assertNotNull(hospitalController.showMap(1L));

        verify(hospitalService, times(1))
                .generateSVG(1L);
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

    Set<Room> createRooms() {
        Room room = new Room();
        room.setBookingStatus(true);
        room.setRoomId(1L);
        room.setNumberFloor(1);
        room.setNumberRoom(101);
        room.setDescription("description");
        room.setType(Type.OPERATING);
        return Set.of(room);
    }
}