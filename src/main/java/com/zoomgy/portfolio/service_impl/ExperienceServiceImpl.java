package com.zoomgy.portfolio.service_impl;
import com.zoomgy.portfolio.entity.Experience;
import com.zoomgy.portfolio.exception.ExperienceNotFoundException;
import com.zoomgy.portfolio.repository.ExperienceRepository;
import com.zoomgy.portfolio.service.ExperienceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceServiceImpl(
            ExperienceRepository experienceRepository
    ) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public List<Experience> getAllExperiences() {

        return experienceRepository
                .findAll()
                ;
    }

    @Override
    public Experience getExperienceById(Long id) throws ExperienceNotFoundException {

        return experienceRepository
                .findById(id)
                .orElseThrow(() ->
                        new ExperienceNotFoundException(
                                "Experience not found with id: " + id
                        )
                );


    }

    @Override
    public Experience createExperience(
            Experience experience
    ) {
        experience.setCreatedAt(LocalDateTime.now());
        experience.setUpdatedAt(LocalDateTime.now());
        return experienceRepository.save(experience);

    }

    @Override
    public Experience updateExperience(
            Long id,
            Experience dto
    ) throws ExperienceNotFoundException {

        Experience experience = experienceRepository
                .findById(id)
                .orElseThrow(() ->
                        new ExperienceNotFoundException(
                                "Experience not found with id: " + id
                        )
                );

        if (dto.getCompanyName() != null) {
            experience.setCompanyName(dto.getCompanyName());
        }

        if (dto.getRole() != null) {
            experience.setRole(dto.getRole());
        }

        if (dto.getDescription() != null) {
            experience.setDescription(dto.getDescription());
        }

        if (dto.getStartDate() != null) {
            experience.setStartDate(dto.getStartDate());
        }

        if (dto.getEndDate() != null) {
            experience.setEndDate(dto.getEndDate());
        }

        if (dto.getCurrentlyWorking() != null) {
            experience.setCurrentlyWorking(
                    dto.getCurrentlyWorking()
            );
        }

        if (dto.getLocation() != null) {
            experience.setLocation(dto.getLocation());
        }

        experience.setUpdatedAt(LocalDateTime.now());

        return experienceRepository.save(experience);
    }

    @Override
    public void deleteExperience(Long id) throws ExperienceNotFoundException {

        Experience experience = experienceRepository
                .findById(id)
                .orElseThrow(() ->
                        new ExperienceNotFoundException(
                                "Experience not found with id: " + id
                        )
                );

        experienceRepository.delete(experience);
    }
}