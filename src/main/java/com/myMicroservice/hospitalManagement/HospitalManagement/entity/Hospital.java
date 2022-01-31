package com.myMicroservice.hospitalManagement.HospitalManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "Hospitals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hospital {

    @ApiModelProperty(notes = "Unique identifier of the Hospital.",
            example = "1", required = true, position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospital_id;

    @ApiModelProperty(notes = "Name hospital. Cannot empty.",
            example = "Hospital 1", required = true, position = 0 )
    @Column(name = "name")
    @NotBlank(message = "Hospital name is mandatory")
    @Size(min = 2, max = 100, message = "min = 2, max = 100")
    private String name;

    @ApiModelProperty(notes = "Address hospital. Cannot empty.",
            example = "Pushkinskaya str., 47.", required = true, position = 0 )
    @Column(name = "address")
    @NotBlank(message = "Hospital address is mandatory")
    private String address;

    @ApiModelProperty(notes = "Email hospital. Cannot empty.",
            example = "best@gmail.com", required = true, position = 0 )
    @Column(name = "email")
    @NotBlank(message = "Hospital email is mandatory")
    @Email(message = "Check hospital message. Example: hospitalname@gmail.com")
    private String email;

    @ApiModelProperty(notes = "Phone hospital. Cannot empty.",
            example = "+380958335927", required = true, position = 0 )
    @Column(name = "phone")
    @NotBlank(message = "Hospital phone is mandatory")
    @Pattern(regexp = "(\\+380*)\\d{9}", message = "Only digits. Example phone: +380123456789")
    private String phone;

    @ApiModelProperty(notes = "Description hospital. Cannot empty.",
            example = "This hospital1 is the best in Ukraine ", required = true, position = 0 )
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Room> rooms;
}
