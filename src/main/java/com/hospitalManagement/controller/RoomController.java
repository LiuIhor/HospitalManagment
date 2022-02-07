package com.hospitalManagement.controller;

import com.hospitalManagement.dto.RoomDTO;
import com.hospitalManagement.entity.Room;
import com.hospitalManagement.service.RoomService;
import com.hospitalManagement.utils.ConvertRoomUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RoomController - controller that contains endpoints for Creating, Retrieving, Updating and Deleting of Rooms.
 */
@Tag(description = "Endpoints for Creating, Retrieving, Updating and Deleting of Rooms.", name = "Room")
@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping(value = "/{hospitalId}/rooms",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds all rooms by hospital id",
            tags = {"Room"})
    @ResponseStatus(HttpStatus.OK)
    public List<RoomDTO> showAllRoomsByHospitalId(@Parameter(description = "The parameter is needed to get all rooms by  hospital id")
                                                  @PathVariable Long hospitalId,
                                                  @RequestParam(required = false, defaultValue = "all") String book) {
        List<Room> rooms = roomService.showAllRoomFilterStatus(book, hospitalId);
        return rooms.stream()
                .map(ConvertRoomUtils::convertToDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/rooms/{roomId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete room by hospital id",
            tags = {"Room"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoomFromHospitalById(@Parameter(description = "The parameter is needed to delete room by id")
                                           @PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
    }

    @GetMapping(value = "/rooms/{roomId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds room by id",
            tags = {"Room"})
    @ResponseStatus(HttpStatus.OK)
    public RoomDTO showRoomById(@Parameter(description = "The parameter is needed to get room by id")
                                @PathVariable Long roomId) {
        return ConvertRoomUtils.convertToDTO(roomService.getRoomById(roomId));
    }

    @PostMapping(value = "/rooms",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add new room",
            tags = {"Room"})
    @ResponseStatus(HttpStatus.OK)
    public RoomDTO addRoom(@Parameter(description = "Object room to be written to the DB",
            required = true, schema = @Schema(implementation = Room.class))
                        @Valid @RequestBody RoomDTO roomDTO) {
        Room room = roomService.addRoom(ConvertRoomUtils.convertToEntity(roomDTO));
        return ConvertRoomUtils.convertToDTO(room);
    }

    @PutMapping(value = "/rooms/{roomId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Changes room",
            tags = {"Room"})
    @ResponseStatus(HttpStatus.OK)
    public RoomDTO changeRoom(@Parameter(description = "he parameter is needed to change room by id")
                           @PathVariable long roomId,
                           @Parameter(description = "Changed object room to be written to the DB",
                                   required = true, schema = @Schema(implementation = Room.class))
                           @Valid @RequestBody RoomDTO roomDTO) {
        roomDTO.setRoomId(roomId);
        Room room = roomService.editRoom(ConvertRoomUtils.convertToEntity(roomDTO));
        return ConvertRoomUtils.convertToDTO(room);
    }

    @PostMapping(value = "/rooms/{roomId}/book",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Books room by id", tags = {"Room"})
    @ResponseStatus(HttpStatus.OK)
    public RoomDTO bookRoom(@Parameter(description = "The parameter is needed to book room in hospital by id")
                         @PathVariable Long roomId) {
        Room room = roomService.bookRoom(roomId);
        return ConvertRoomUtils.convertToDTO(room);
    }
}
