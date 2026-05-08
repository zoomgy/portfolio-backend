package com.zoomgy.portfolio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Institution Name is required")
    @Size(min = 2, max = 50,
            message = "Institution Name must be between 2 and 50 characters")
    private String institutionName;
    @NotBlank(message = "Degree Name is required")
    @Size(min = 2, max = 50,
            message = "Degree Name must be between 2 and 50 characters")
    private String degree;
    private String fieldOfStudy;
    @NotNull(message = "Start Year is required")
    private Long startYear;
    private Long endYear;
    private Float cgpa;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
