package com.zoomgy.portfolio.service_impl;

import com.zoomgy.portfolio.entity.Skill;
import com.zoomgy.portfolio.exception.SkillNotFoundException;
import com.zoomgy.portfolio.repository.SkillRepository;
import com.zoomgy.portfolio.service.SkillService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(
            SkillRepository skillRepository
    ) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillById(Long id) throws SkillNotFoundException {

        return skillRepository.findById(id)
                .orElseThrow(() ->
                        new SkillNotFoundException(
                                "Skill Not Found"
                        ));
    }

    @Override
    public Skill createSkill(Skill skill) {
        System.out.println(skill.getProficiency());

        skill.setCreatedAt(LocalDateTime.now());
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Long id, Skill skill) throws SkillNotFoundException {

        Skill existingSkill = skillRepository.findById(id)
                .orElseThrow(() ->
                        new SkillNotFoundException(
                                "Skill Not Found"
                        ));

        if (skill.getName() != null &&
                !skill.getName().isBlank()) {

            existingSkill.setName(skill.getName());
        }

        if (skill.getProficiency() != null) {

            existingSkill.setProficiency(
                    skill.getProficiency()
            );
        }

        existingSkill.setUpdatedAt(LocalDateTime.now());

        return skillRepository.save(existingSkill);
    }

    @Override
    public void deleteSkill(Long id) throws SkillNotFoundException {

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() ->
                        new SkillNotFoundException(
                                "Skill Not Found"
                        ));

        skillRepository.delete(skill);
    }
}