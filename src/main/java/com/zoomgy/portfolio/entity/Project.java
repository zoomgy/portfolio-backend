package com.zoomgy.portfolio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100,
            message = "Title must be between 3 and 100 characters")
    @Column(unique = true)
    private String title;

    @NotBlank(message = "Short description is required")
    @Size(max = 200,
            message = "Short description cannot exceed 200 characters")
    private String shortDescription;

    @NotBlank(message = "Full description is required")
    private String fullDescription;

    private String githubUrl;

    private String liveUrl;

    @ElementCollection
    @NotEmpty(message = "At least one image URL is required")
    private List<String> imageUrls;

    @ElementCollection
    @NotEmpty(message = "At least one tech stack item is required")
    private List<String> techStack;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}