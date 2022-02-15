package com.hospitalManagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The HospitalDTO class represents the hospital DTO.
 */
@Data
public class HospitalDTO {

    @Schema(description = "Hospital id.",
            example = "1", required = true)
    private Long hospitalId;

    @Schema(description = "Name hospital. Can`t be empty.",
            example = "Hospital 1", required = true)
    @NotBlank(message = "Hospital name is mandatory")
    @Size(min = 2, max = 100, message = "min = 2, max = 100")
    private String name;

    @Schema(description = "Address hospital. Can`t be empty.",
            example = "Pushkinskaya str., 47.", required = true)
    @NotBlank(message = "Hospital address is mandatory")
    private String address;

    @Schema(description = "Email hospital. Can`t be empty.",
            example = "best@gmail.com", required = true)
    @NotBlank(message = "Hospital email is mandatory")
    @Email(message = "Check hospital message. Example: hospitalname@gmail.com")
    private String email;

    @Schema(description = "Phone hospital. Can`t be empty.",
            example = "+380958335927", required = true)
    @NotBlank(message = "Hospital phone is mandatory")
    @Pattern(regexp = "(\\+380*)\\d{9}", message = "Only digits. Example phone: +380123456789")
    private String phone;

    @Schema(description = "Description hospital. Can`t be empty.",
            example = "This hospital1 is the best in Ukraine ", required = true)
    @NotBlank(message = "Hospital description is mandatory")
    private String description;
}
