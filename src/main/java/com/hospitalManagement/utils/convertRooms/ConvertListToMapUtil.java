package com.hospitalManagement.utils.convertRooms;

import com.hospitalManagement.entity.Room;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class to convert list to map
 */
@UtilityClass
public class ConvertListToMapUtil {

    /**
     * conver - method to convert list to map
     *
     * @param rooms To be converted to map
     * @return Converted list map
     */
    public Map<Integer, List<Room>> convert(List<Room> rooms) {
        return rooms.stream()
                .collect(Collectors.groupingBy(Room::getNumberFloor,
                        HashMap::new, Collectors.toList()));
    }
}
