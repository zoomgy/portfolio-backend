package com.zoomgy.portfolio.repository;

import com.zoomgy.portfolio.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill,Long> {
}
