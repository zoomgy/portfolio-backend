package com.zoomgy.portfolio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Company Name is required")
    @Size(min = 2, max = 50,
            message = "Company Name must be between 2 and 50 characters")
    private String companyName;
    @NotBlank(message = "Role is required")
    @Size(min = 2, max = 50,
            message = "Role must be between 2 and 50 characters")
    private String role;
    private String fieldOfStudy;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean currentlyWorking;
    private String location;
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
