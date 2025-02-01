package com.taskmanager.app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {
    private String title;
    private String description;
    private String deadline;
}
