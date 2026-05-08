package com.zoomgy.portfolio.service;

import com.zoomgy.portfolio.entity.Skill;
import com.zoomgy.portfolio.exception.SkillNotFoundException;
import jakarta.validation.Valid;

import java.util.List;

public interface SkillService {
    List<Skill> getAllSkills();
    Skill createSkill(Skill skill);
    void deleteSkill(Long id) throws SkillNotFoundException;
    Skill updateSkill(Long id, @Valid Skill skill) throws SkillNotFoundException;

    Skill getSkillById(Long id) throws SkillNotFoundException;
}
