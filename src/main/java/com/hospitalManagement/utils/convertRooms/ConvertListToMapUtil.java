package com.hospitalManagement.utils.convertRooms;

import com.hospitalManagement.entity.Room;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class ConvertListToMapUtil {

    public Map<Integer, List<Room>> convert(List<Room> rooms) {
        return rooms.stream()
                .collect(Collectors.groupingBy(Room::getNumberFloor,
                        HashMap::new, Collectors.toList()));
    }
}
