package com.hospitalManagement.utils.modelMapper;

import com.hospitalManagement.dto.RoomDTO;
import com.hospitalManagement.entity.Room;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

/**
 * Class converter from entity to dto
 */
@UtilityClass
public class ConvertRoomUtil {

    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * method to convert Room from dto to entity
     *
     * @param roomDTO dto to be converted to entity
     * @return Room entity
     */
    public Room convertToEntity(RoomDTO roomDTO) {
        return modelMapper.map(roomDTO, Room.class);
    }

    /**
     * method to convert Room from entity to dto
     *
     * @param room entity to be converted to dto
     * @return Room dto
     */
    public RoomDTO convertToDTO(Room room) {
        return modelMapper.map(room, RoomDTO.class);
    }
}
