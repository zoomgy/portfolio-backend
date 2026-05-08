package com.zoomgy.portfolio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Skill name is required")
    @Size(min = 2, max = 50,
            message = "Skill name must be between 2 and 50 characters")
    @Column(unique = true)
    private String name;

    @Min(value = 1,
            message = "Proficiency must be at least 1")
    @Max(value = 100,
            message = "Proficiency cannot exceed 100")
    private Long proficiency;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}