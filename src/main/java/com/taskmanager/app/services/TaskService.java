package com.taskmanager.app.services;

import com.taskmanager.app.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<TaskEntity>();
    private int taskId = 1;

    public TaskEntity addTask(String title,String description,String deadline){
        TaskEntity task = new TaskEntity();
        task.setId(this.taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadline); // TODO: validate date format
        task.setCompleted(false);
        this.taskId++;
        this.tasks.add(task);
        return task;
    }

    public List<TaskEntity> getAllTasks(){
        return this.tasks;
    }

    public TaskEntity getTaskById(Integer id){
        try{
            return this.tasks.stream()
                    .filter(task -> task.getId() == id)
                    .findFirst()
                    .orElse(null);
        }catch(Exception e){
            System.out.println("error in task service");
        }
        return null;
    }
}


























