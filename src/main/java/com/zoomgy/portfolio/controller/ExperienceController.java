package com.zoomgy.portfolio.controller;

import com.zoomgy.portfolio.entity.Experience;
import com.zoomgy.portfolio.exception.ExperienceNotFoundException;
import com.zoomgy.portfolio.service.ExperienceService;
import com.zoomgy.portfolio.utils.CustomResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(
            ExperienceService experienceService
    ) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllExperiences() {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        experienceService.getAllExperiences(),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getExperienceById(
            @PathVariable Long id
    ) throws ExperienceNotFoundException {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        experienceService.getExperienceById(id),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createExperience(
            @Valid @RequestBody Experience experience
    ) {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        experienceService.createExperience(experience),
                        HttpStatus.CREATED.value()
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateExperience(
            @PathVariable Long id,
            @RequestBody Experience experience
    ) throws ExperienceNotFoundException {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        experienceService.updateExperience(
                                id,
                                experience
                        ),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteExperience(
            @PathVariable Long id
    ) throws ExperienceNotFoundException {

        experienceService.deleteExperience(id);

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        "Deleted Successfully",
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }
}