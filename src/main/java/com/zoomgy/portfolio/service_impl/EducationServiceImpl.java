package com.zoomgy.portfolio.service_impl;

import com.zoomgy.portfolio.entity.Education;
import com.zoomgy.portfolio.exception.EducationNotFoundException;
import com.zoomgy.portfolio.repository.EducationRepository;
import com.zoomgy.portfolio.service.EducationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;

    public EducationServiceImpl(
            EducationRepository educationRepository
    ) {
        this.educationRepository = educationRepository;
    }

    @Override
    public List<Education> getAllEducation() {

        return educationRepository
                .findAll();
    }

    @Override
    public Education getEducationById(Long id) throws EducationNotFoundException {

        return educationRepository
                .findById(id)
                .orElseThrow(() ->
                        new EducationNotFoundException(
                                "Education not found with id: " + id
                        )
                );
    }

    @Override
    public Education createEducation(Education education) {
        education.setCreatedAt(LocalDateTime.now());
        education.setUpdatedAt(LocalDateTime.now());

        return educationRepository.save(education);
    }

    @Override
    public Education updateEducation(
            Long id,
            Education dto
    ) throws EducationNotFoundException {

        Education education = educationRepository
                .findById(id)
                .orElseThrow(() ->
                        new EducationNotFoundException(
                                "Education not found with id: " + id
                        )
                );

        if (dto.getInstitutionName() != null) {
            education.setInstitutionName(
                    dto.getInstitutionName()
            );
        }

        if (dto.getDegree() != null) {
            education.setDegree(dto.getDegree());
        }

        if (dto.getFieldOfStudy() != null) {
            education.setFieldOfStudy(
                    dto.getFieldOfStudy()
            );
        }

        if (dto.getStartYear() != null) {
            education.setStartYear(
                    dto.getStartYear()
            );
        }

        if (dto.getEndYear() != null) {
            education.setEndYear(
                    dto.getEndYear()
            );
        }

        if (dto.getCgpa() != null) {
            education.setCgpa(dto.getCgpa());
        }

        if (dto.getDescription() != null) {
            education.setDescription(
                    dto.getDescription()
            );
        }

        education.setUpdatedAt(LocalDateTime.now());

        return educationRepository.save(education);
    }

    @Override
    public void deleteEducation(Long id) throws EducationNotFoundException {

        Education education = educationRepository
                .findById(id)
                .orElseThrow(() ->
                        new EducationNotFoundException(
                                "Education not found with id: " + id
                        )
                );

        educationRepository.delete(education);
    }
}