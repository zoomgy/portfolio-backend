package com.zoomgy.portfolio.service;

import com.zoomgy.portfolio.entity.Project;
import com.zoomgy.portfolio.exception.ProjectNotFoundException;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    Project getProjectById(Long id) throws ProjectNotFoundException;
    Project createProject(Project project);
    Project updateProject(Long id,Project project) throws ProjectNotFoundException;
    void deleteProject(Long id) throws ProjectNotFoundException;
}
