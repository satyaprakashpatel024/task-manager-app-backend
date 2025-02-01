package com.taskmanager.app.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequestDTO {
    private String title;
    private String body;
}
