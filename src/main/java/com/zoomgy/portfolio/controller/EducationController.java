package com.zoomgy.portfolio.controller;

import com.zoomgy.portfolio.entity.Education;
import com.zoomgy.portfolio.exception.EducationNotFoundException;
import com.zoomgy.portfolio.service.EducationService;
import com.zoomgy.portfolio.utils.CustomResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    public EducationController(
            EducationService educationService
    ) {
        this.educationService = educationService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEducation() {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        educationService.getAllEducation(),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEducationById(
            @PathVariable Long id
    ) throws EducationNotFoundException {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        educationService.getEducationById(id),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createEducation(
            @Valid @RequestBody Education education
    ) {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        educationService.createEducation(education),
                        HttpStatus.CREATED.value()
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateEducation(
            @PathVariable Long id,
            @RequestBody Education education
    ) throws EducationNotFoundException {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        educationService.updateEducation(id, education),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEducation(
            @PathVariable Long id
    ) throws EducationNotFoundException {

        educationService.deleteEducation(id);

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        "Deleted Successfully",
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }
}