package com.example.help_with_my_tasks.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class TaskRequestDto {
    private String taskName;
    private String taskDescription;
    private Date taskDueDate;
}
