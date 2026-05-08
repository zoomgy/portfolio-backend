package com.zoomgy.portfolio.service;

import com.zoomgy.portfolio.entity.Experience;
import com.zoomgy.portfolio.exception.ExperienceNotFoundException;

import java.util.List;

public interface ExperienceService {

    List<Experience> getAllExperiences();

    Experience getExperienceById(Long id) throws ExperienceNotFoundException;

    Experience createExperience(Experience dto);

    Experience updateExperience(Long id, Experience dto) throws ExperienceNotFoundException;

    void deleteExperience(Long id) throws ExperienceNotFoundException;
}