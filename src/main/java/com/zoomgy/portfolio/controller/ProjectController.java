package com.zoomgy.portfolio.controller;

import com.zoomgy.portfolio.entity.Project;
import com.zoomgy.portfolio.exception.ProjectNotFoundException;
import com.zoomgy.portfolio.service.ProjectService;
import com.zoomgy.portfolio.utils.CustomResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProjects() {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        projectService.getAllProjects(),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProjectById(@PathVariable Long id) throws ProjectNotFoundException {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        projectService.getProjectById(id),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProject(@Valid @RequestBody Project project) {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        projectService.createProject(project),
                        HttpStatus.CREATED.value()
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody Project project
    ) throws ProjectNotFoundException {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        projectService.updateProject(id, project),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProject(@PathVariable Long id) throws ProjectNotFoundException {

        projectService.deleteProject(id);

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        "Deleted Successfully",
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }
}