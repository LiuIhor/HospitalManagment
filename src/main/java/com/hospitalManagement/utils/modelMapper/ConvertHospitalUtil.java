package com.hospitalManagement.utils.modelMapper;

import com.hospitalManagement.dto.HospitalDTO;
import com.hospitalManagement.entity.Hospital;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

/**
 * Class converter from entity to dto
 */
@UtilityClass
public class ConvertHospitalUtil {

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * method to convert Hospital from dto to entity
     *
     * @param hospitalDTO dto to be converted to entity
     * @return Hospital entity
     */
    public Hospital convertToEntity(HospitalDTO hospitalDTO) {
        return modelMapper.map(hospitalDTO, Hospital.class);
    }

    /**
     * method to convert Hospital from entity to dto
     *
     * @param hospital entity to be converted to dto
     * @return Hospital dto
     */
    public HospitalDTO convertToDTO(Hospital hospital) {
        return modelMapper.map(hospital, HospitalDTO.class);
    }
}
