package com.zoomgy.portfolio.dto;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;
    @Size(max = 200, message = "Short description cannot exceed 200 characters")
    private String shortDescription;
    private String fullDescription;
    private String githubUrl;
    private String liveUrl;
    @ElementCollection
    private List<String> imageUrls;
    @ElementCollection
    private List<String> techStack;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}