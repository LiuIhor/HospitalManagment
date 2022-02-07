package com.hospitalManagement.controller;

import com.hospitalManagement.dto.HospitalDTO;
import com.hospitalManagement.entity.Hospital;
import com.hospitalManagement.service.HospitalService;

import com.hospitalManagement.utils.ConvertHospitalUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

/**
 * HospitalController - controller that contains endpoints for Creating, Retrieving, Updating and Deleting of Hospitals.
 */
@Tag(description = "Endpoints for Creating, Retrieving, Updating and Deleting of Hospitals.",
        name = "Hospital")
@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping(value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Show all hospitals ",
            tags = {"Hospital"})
    @ResponseStatus(HttpStatus.OK)
    public List<HospitalDTO> showAllHospitals() {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        return hospitals.stream()
                .map(ConvertHospitalUtil::convertToDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/{hospitalId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete hospital by Id",
            tags = {"Hospital"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHospitalById(@Parameter(description = "The parameter is needed to delete hospital by id")
                                   @PathVariable Long hospitalId) {
        hospitalService.deleteHospital(hospitalId);
    }

    @GetMapping(value = "/{hospitalId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds hospital by id",
            tags = {"Hospital"})
    @ResponseStatus(HttpStatus.OK)
    public HospitalDTO showHospitalById(@Parameter(description = "The parameter is needed to get hospital by id")
                                        @PathVariable Long hospitalId) {
        return ConvertHospitalUtil.convertToDTO(hospitalService.getHospitalById(hospitalId));
    }

    @PostMapping(value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Add new hospital",
            tags = {"Hospital"})
    @ResponseStatus(HttpStatus.OK)
    public HospitalDTO addHospital(@Parameter(name = "Object hospital to be written to the DB",
            required = true, schema = @Schema(implementation = HospitalDTO.class))
                                @Valid @RequestBody HospitalDTO hospitalDTO) {
        Hospital hospital = hospitalService.addHospital(ConvertHospitalUtil.convertToEntity(hospitalDTO));
        return ConvertHospitalUtil.convertToDTO(hospital);
    }

    @PutMapping(value = "/{hospitalId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Changes hospital",
            tags = {"Hospital"})
    @ResponseStatus(HttpStatus.OK)
    public HospitalDTO changeHospital(@Parameter(description = "The parameter is needed to change hospital by id")
                                   @PathVariable Long hospitalId,
                                   @Parameter(name = "Changed object hospital to be written to the DB",
                                           required = true, schema = @Schema(implementation = HospitalDTO.class))
                                   @Valid @RequestBody HospitalDTO hospitalDTO) {
        hospitalDTO.setHospitalId(hospitalId);
        Hospital hospital = hospitalService.editHospital(ConvertHospitalUtil.convertToEntity(hospitalDTO));
        return ConvertHospitalUtil.convertToDTO(hospital);
    }

    //Will be implemented in the next stories
    @GetMapping(value = "/{hospitalId}/map",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Returns data for map of hospital as graph",
            tags = {"Hospital"})
    public ResponseEntity<Void> showMapAsGraph(@Parameter(description = "The parameter is needed to get data hospital by id")
                                               @PathVariable Long hospitalId) {
        return null;
    }

    //Will be implemented in the next stories
    @GetMapping(value = "/{hospitalId}/map-svg",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Generate actual map as svg picture",
            tags = {"Hospital"})
    public ResponseEntity<Void> generateSvgMap(@Parameter(description = "The parameter is needed to generate actual map" +
            " of hospital by id")
                                               @PathVariable Long hospitalId) {
        return null;
    }
}
