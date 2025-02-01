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

    private int id;
    private String title;
    private String description;
    private Date deadline;
//    private List<NoteEntity> notes;
    private boolean completed;
}
