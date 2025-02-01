package com.taskmanager.app.entity;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {

    @Getter
    @Setter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Note{
        private String title;
        private String body;
    }

    private int id;
    private String title;
    private String description;
    private String deadline;
    private List<Note> notes;
    private boolean completed;
}
