package com.hospitalManagement.utils.modelMapper;

import com.hospitalManagement.dto.HospitalDTO;
import com.hospitalManagement.entity.Hospital;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

@UtilityClass
public class ConvertHospitalUtil {

    private final ModelMapper modelMapper = new ModelMapper();

    public Hospital convertToEntity(HospitalDTO orderDTO) {
        return modelMapper.map(orderDTO, Hospital.class);
    }

    public HospitalDTO convertToDTO(Hospital hospital) {
        return modelMapper.map(hospital, HospitalDTO.class);
    }
}
