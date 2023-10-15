package com.example.help_with_my_tasks.controllers;

import com.example.help_with_my_tasks.dtos.TaskRequestDto;
import com.example.help_with_my_tasks.dtos.TaskResponseDto;
import com.example.help_with_my_tasks.exceptions.NotFoundException;
import com.example.help_with_my_tasks.models.HelpSeeker;
import com.example.help_with_my_tasks.models.Task;
import com.example.help_with_my_tasks.services.service_interfaces.HelpSeekerService;
import com.example.help_with_my_tasks.services.service_interfaces.TaskService;
import com.example.help_with_my_tasks.utilities.TaskUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final HelpSeekerService helpSeekerService;

    public TaskController(TaskService taskService, HelpSeekerService helpSeekerService) {
        this.taskService = taskService;
        this.helpSeekerService = helpSeekerService;
    }

    @PostMapping("/{helpSeekerId}")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto,
                                                      @PathVariable(name = "helpSeekerId") Long helpSeekerId)
            throws NotFoundException {
        Task task = TaskUtility.convertTaskRequestDtoToTask(taskRequestDto);
        Optional<HelpSeeker> helpSeekerOptional = helpSeekerService.getHelpSeekerById(helpSeekerId);
        if (helpSeekerOptional.isEmpty()){
            throw new NotFoundException("HelpSeeker not found");
        }
        HelpSeeker helpSeeker = helpSeekerOptional.get();

        Optional<Task> optionalTask = taskService.createTask(task, helpSeeker);
        if (optionalTask.isEmpty()){
            throw new NotFoundException("Task not created");
        }
        TaskResponseDto taskResponseDto = TaskUtility.convertTaskToTaskResponseDto(optionalTask.get());
        return new ResponseEntity<>(taskResponseDto, HttpStatus.OK);
    }



}
