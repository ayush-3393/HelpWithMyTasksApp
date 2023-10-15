package com.example.help_with_my_tasks.dtos;

import com.example.help_with_my_tasks.models.enums.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TaskResponseDto {
    private Long taskId;
    private String taskTitle;
    private String taskDescription;
    private String helpSeekerName;
    private TaskStatus taskStatus;
    private Date taskDueDate;
}
