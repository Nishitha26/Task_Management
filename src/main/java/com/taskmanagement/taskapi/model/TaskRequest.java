package com.taskmanagement.taskapi.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskRequest {
    @NotBlank(message = "title is required")
    private String title;
    private String description;
    @NotNull(message = "status is required")
    private String status;
}
