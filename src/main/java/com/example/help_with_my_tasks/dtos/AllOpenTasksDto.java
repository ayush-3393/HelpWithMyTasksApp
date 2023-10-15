package com.example.help_with_my_tasks.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class AllOpenTasksDto {
    private List<TaskResponseDto> tasks;
}
