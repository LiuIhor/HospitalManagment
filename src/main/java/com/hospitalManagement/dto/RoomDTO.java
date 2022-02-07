package com.hospitalManagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoomDTO {

    @Schema(description = "Unique identifier of the Room.",
            example = "1", required = true)
    private Long roomId;

    @Schema(description = "Id hospital. Can`t be empty",
            example = "1", required = true)
    @NotNull(message = "Hospital where the room is located is mandatory")
    private Long hospitalId;

    @Schema(description = "Number room. Can`t be empty",
            example = "102", required = true)
    @NotNull(message = "Room number is mandatory")
    private int numberRoom;

    @Schema(description = "floor of room. Can`t be empty",
            example = "1", required = true)
    @NotNull(message = "Floor number is mandatory")
    private int numberFloor;


    @Schema(description = "Room description. Can`t be empty",
            example = "This is the reception room", required = true)
    @NotBlank(message = "Room description is mandatory")
    private String description;

    @Schema(description = "Room status.",
            example = "true", required = true)
    private boolean bookingStatus = false;
}
