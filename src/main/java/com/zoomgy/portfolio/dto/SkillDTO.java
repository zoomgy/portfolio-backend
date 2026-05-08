package com.zoomgy.portfolio.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {


    @Size(min = 2, max = 50, message = "Skill name must be between 2 and 50 characters")
    private String name;
    @Min(value = 1, message = "Proficiency must be at least 1")
    @Max(value = 100, message = "Proficiency cannot exceed 100")
    private Integer proficiency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}