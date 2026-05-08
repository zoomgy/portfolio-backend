package com.zoomgy.portfolio.service_impl;

import com.zoomgy.portfolio.entity.Project;
import com.zoomgy.portfolio.exception.ProjectNotFoundException;
import com.zoomgy.portfolio.repository.ProjectRepository;
import com.zoomgy.portfolio.service.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectServiceImpl
        implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(
            ProjectRepository projectRepository
    ) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) throws ProjectNotFoundException {

        return projectRepository.findById(id)
                .orElseThrow(() ->
                        new ProjectNotFoundException(
                                "Project Not Found"
                        ));
    }

    @Override
    public Project createProject(Project project) {

        project.setCreatedAt(LocalDateTime.now());

        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(
            Long id,
            Project newProject
    ) throws ProjectNotFoundException {

        Project existingProject =
                projectRepository.findById(id)
                        .orElseThrow(() ->
                                new ProjectNotFoundException(
                                        "Project Not Found"
                                ));

        if (newProject.getTitle() != null &&
                !newProject.getTitle().isBlank()) {

            existingProject.setTitle(
                    newProject.getTitle()
            );
        }

        if (newProject.getShortDescription() != null &&
                !newProject.getShortDescription().isBlank()) {

            existingProject.setShortDescription(
                    newProject.getShortDescription()
            );
        }

        if (newProject.getFullDescription() != null &&
                !newProject.getFullDescription().isBlank()) {

            existingProject.setFullDescription(
                    newProject.getFullDescription()
            );
        }

        if (newProject.getGithubUrl() != null &&
                !newProject.getGithubUrl().isBlank()) {

            existingProject.setGithubUrl(
                    newProject.getGithubUrl()
            );
        }

        if (newProject.getLiveUrl() != null &&
                !newProject.getLiveUrl().isBlank()) {

            existingProject.setLiveUrl(
                    newProject.getLiveUrl()
            );
        }

        if (newProject.getImageUrls() != null &&
                !newProject.getImageUrls().isEmpty()) {

            existingProject.setImageUrls(
                    newProject.getImageUrls()
            );
        }

        if (newProject.getTechStack() != null &&
                !newProject.getTechStack().isEmpty()) {

            existingProject.setTechStack(
                    newProject.getTechStack()
            );
        }

        existingProject.setUpdatedAt(
                LocalDateTime.now()
        );

        return projectRepository.save(
                existingProject
        );
    }

    @Override
    public void deleteProject(Long id) throws ProjectNotFoundException {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ProjectNotFoundException(
                                "Project Not Found"
                        ));

        projectRepository.delete(project);
    }
}