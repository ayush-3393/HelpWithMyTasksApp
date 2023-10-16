package com.example.help_with_my_tasks.dtos;

import com.example.help_with_my_tasks.models.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class TaskRequestDto {
    private String taskName;
    private String taskDescription;
    private TaskStatus taskStatus;
    private Integer taskBudget;
    private Date taskDueDate;
}
