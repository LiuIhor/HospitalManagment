package com.hospitalManagement.utils;

import com.hospitalManagement.dto.RoomDTO;
import com.hospitalManagement.entity.Room;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

@UtilityClass
public class ConvertRoomUtils {

    private final ModelMapper modelMapper = new ModelMapper();

    public Room convertToEntity(RoomDTO roomDTO) {
        return modelMapper.map(roomDTO, Room.class);
    }

    public RoomDTO convertToDTO(Room room) {
        return modelMapper.map(room, RoomDTO.class);
    }
}
