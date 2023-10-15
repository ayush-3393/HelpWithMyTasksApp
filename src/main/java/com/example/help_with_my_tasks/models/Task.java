package com.example.help_with_my_tasks.models;

import com.example.help_with_my_tasks.models.enums.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Task extends BaseModel{
    private String taskTitle;
    private String taskDescription;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    private Date taskDueDate;

    @ManyToOne
    private HelpSeeker helpSeeker;
}
