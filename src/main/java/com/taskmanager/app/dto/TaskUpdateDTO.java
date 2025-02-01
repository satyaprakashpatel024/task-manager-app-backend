package com.taskmanager.app.dto;

import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateDTO {
    private String description;
    private String deadline;
    private boolean completed;

    public boolean getCompleted() {
        return this.completed;
    }
}
