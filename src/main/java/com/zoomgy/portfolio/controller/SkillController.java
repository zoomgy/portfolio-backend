package com.zoomgy.portfolio.controller;

import com.zoomgy.portfolio.entity.Skill;
import com.zoomgy.portfolio.exception.SkillNotFoundException;
import com.zoomgy.portfolio.service.SkillService;
import com.zoomgy.portfolio.utils.CustomResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllSkills() {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        skillService.getAllSkills(),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getSkillById(
            @PathVariable Long id
    ) throws SkillNotFoundException {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        skillService.getSkillById(id),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createSkill(
            @Valid @RequestBody Skill skill
    ) {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        skillService.createSkill(skill),
                        HttpStatus.CREATED.value()
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateSkill(
            @PathVariable Long id,
            @Valid @RequestBody Skill skill
    ) throws SkillNotFoundException {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        skillService.updateSkill(id, skill),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteSkill(
            @PathVariable Long id
    ) throws SkillNotFoundException {

        skillService.deleteSkill(id);

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        "Deleted Successfully",
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }
}