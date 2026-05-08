package com.zoomgy.portfolio.repository;

import com.zoomgy.portfolio.entity.Education;
import com.zoomgy.portfolio.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience,Long> {
}
