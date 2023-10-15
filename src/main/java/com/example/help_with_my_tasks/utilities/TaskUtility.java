package com.example.help_with_my_tasks.utilities;

import com.example.help_with_my_tasks.dtos.TaskRequestDto;
import com.example.help_with_my_tasks.dtos.TaskResponseDto;
import com.example.help_with_my_tasks.models.HelpSeeker;
import com.example.help_with_my_tasks.models.Task;

public class TaskUtility {

    public static Task convertTaskRequestDtoToTask(TaskRequestDto taskRequestDto){
        Task task = new Task();
        task.setTaskTitle(taskRequestDto.getTaskName());
        task.setTaskDescription(taskRequestDto.getTaskDescription());
        task.setTaskStatus(taskRequestDto.getTaskStatus());
        task.setTaskDueDate(taskRequestDto.getTaskDueDate());
        return task;
    }

    public static TaskResponseDto convertTaskToTaskResponseDto(Task task){
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setTaskId(task.getId());
        taskResponseDto.setTaskTitle(task.getTaskTitle());
        taskResponseDto.setTaskDescription(task.getTaskDescription());
        taskResponseDto.setTaskStatus(task.getTaskStatus());
        taskResponseDto.setTaskDueDate(task.getTaskDueDate());
        HelpSeeker helpSeeker = task.getHelpSeeker();
        taskResponseDto.setHelpSeekerName(helpSeeker.getFirstName() + " " + helpSeeker.getLastName());
        return taskResponseDto;
    }

}
