package com.taskmanager.app.dto;

import com.taskmanager.app.entity.NoteEntity;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponseDTO {
    private Integer taskId;
    private NoteEntity note;
}
