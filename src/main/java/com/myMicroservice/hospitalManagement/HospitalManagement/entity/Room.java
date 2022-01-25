package com.myMicroservice.hospitalManagement.HospitalManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.xml.crypto.Data;

@Entity
@Table(name = "Rooms")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long room_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hospital_id", nullable = false)
    @NotBlank(message = "Hospital where the room is located is mandatory")
    private Hospital hospital;

    @Column(name = "number_room")
    @NotBlank(message = "Room number is mandatory")
    private long number_room;

    @Column(name = "status")
    private boolean status = false;

//    @Column(name = "booked_from")
//    @Temporal(TemporalType.DATE)
//    private java.util.Date booked_from;
    public Room() {
    }

    public Room(long number_room, boolean status) {
        this.number_room = number_room;
        this.status = status;
    }

    public Room(Hospital hospital, long number_room, boolean status) {
        this.hospital = hospital;
        this.number_room = number_room;
        this.status = status;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public long getNumber_room() {
        return number_room;
    }

    public void setNumber_room(long number_room) {
        this.number_room = number_room;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
