package com.zoomgy.portfolio.service;

import com.zoomgy.portfolio.entity.Education;
import com.zoomgy.portfolio.exception.EducationNotFoundException;

import java.util.List;

public interface EducationService {

    List<Education> getAllEducation();

    Education getEducationById(Long id) throws EducationNotFoundException;

    Education createEducation(Education dto);

    Education updateEducation(Long id, Education dto) throws EducationNotFoundException;

    void deleteEducation(Long id) throws EducationNotFoundException;
}