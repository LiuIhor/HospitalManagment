package com.myMicroservice.hospitalManagement.HospitalManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {

    @ApiModelProperty(notes = "Unique identifier of the Room.",
            example = "1", required = true, position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long room_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hospital_id", nullable = false)
    @NotBlank(message = "Hospital where the room is located is mandatory")
    private Hospital hospital;

    @ApiModelProperty(notes = "Number room. Cannot empty",
            example = "102", required = true, position = 0)
    @Column(name = "number_room")
    @NotBlank(message = "Room number is mandatory")
    private long number_room;

    @ApiModelProperty(notes = "floor of room. Cannot empty",
            example = "1", required = true, position = 0)
    @Column(name = "floor")
    @NotBlank(message = "Floor number is mandatory")
    private long number_floor;

    @ApiModelProperty(notes = "Room description. Cannot empty",
            example = "This is the reception room", required = true, position = 0)
    @Column(name = "description")
    private String description;

    @ApiModelProperty(notes = "Room status.",
            example = "true", required = true, position = 0)
    @Column(name = "booking_status")
    private boolean bookingStatus = false;
}
