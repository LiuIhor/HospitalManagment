package com.myMicroservice.hospitalManagement.HospitalManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "Hospitals")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospital_id;

    @Column(name = "name")
    @NotBlank(message = "Hospital name is mandatory")
    @Size(min = 2, max = 100, message = "min = 2, max = 100")
    private String name;

    @Column(name = "address")
    @NotBlank(message = "Hospital address is mandatory")
    private String address;

    @Column(name = "email")
    @NotBlank(message = "Hospital email is mandatory")
    @Email(message = "Check hospital message. Example: hospitalname@gmail.com")
    private String email;

    @Column(name = "phone")
    @NotBlank(message = "Hospital phone is mandatory")
    @Pattern(regexp = "(\\+380*)\\d{9}", message = "Only digits. Example phone: +380123456789")
    private String phone;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Room> rooms;

    public Hospital() {
    }

    public Hospital(String name, String address, String email, String phone, String description) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }

    public Long getId() {
        return hospital_id;
    }

    public void setId(Long id) {
        this.hospital_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
