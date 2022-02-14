package com.hospitalManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.hospitalManagement.entity.enums.Type;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hospital_id")
    @JsonIgnore
    private Hospital hospital;

    @Column(name = "number_room")
    private int numberRoom;

    @Column(name = "floor")
    private int numberFloor;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "description")
    private String description;

    @Column(name = "booking_status")
    private boolean bookingStatus = false;
}
