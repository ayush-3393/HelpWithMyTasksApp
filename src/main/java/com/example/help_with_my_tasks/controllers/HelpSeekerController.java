package com.example.help_with_my_tasks.controllers;

import com.example.help_with_my_tasks.dtos.AllOpenTasksDto;
import com.example.help_with_my_tasks.dtos.HelpSeekerRequestDto;
import com.example.help_with_my_tasks.dtos.HelpSeekerResponseDto;
import com.example.help_with_my_tasks.dtos.TaskResponseDto;
import com.example.help_with_my_tasks.exceptions.NotFoundException;
import com.example.help_with_my_tasks.models.HelpSeeker;
import com.example.help_with_my_tasks.models.Task;
import com.example.help_with_my_tasks.services.service_interfaces.HelpSeekerService;
import com.example.help_with_my_tasks.services.service_interfaces.TaskService;
import com.example.help_with_my_tasks.utilities.HelpSeekerUtility;
import com.example.help_with_my_tasks.utilities.TaskUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/helpseeker")
public class HelpSeekerController {

    private final HelpSeekerService helpSeekerService;
    private final TaskService taskService;

    public HelpSeekerController(HelpSeekerService helpSeekerService, TaskService taskService) {
        this.helpSeekerService = helpSeekerService;
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<HelpSeekerResponseDto> createHelpSeeker
            (@RequestBody HelpSeekerRequestDto helpSeekerRequestDto) throws NotFoundException {
        HelpSeeker helpSeeker = HelpSeekerUtility.convertHelpSeekerRequestDtoToHelpSeeker(helpSeekerRequestDto);
        Optional<HelpSeeker> optionalHelpSeeker = helpSeekerService.createHelpSeeker(helpSeeker);
        if (optionalHelpSeeker.isEmpty()){
            throw new NotFoundException("Help Seeker not found");
        }
        HelpSeekerResponseDto helpSeekerResponseDto =
                HelpSeekerUtility.convertHelpSeekerToHelpSeekerResponseDto(optionalHelpSeeker.get());
        return new ResponseEntity<>(helpSeekerResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{helpSeekerId}/tasks")
    public ResponseEntity<AllOpenTasksDto> getAllBookedTasksForAHelpSeeker(
            @PathVariable(name = "helpSeekerId") Long helpSeekerId) throws NotFoundException {
        Optional<HelpSeeker> helpSeekerOptional = helpSeekerService.getHelpSeekerById(helpSeekerId);
        if (helpSeekerOptional.isEmpty()){
            throw new NotFoundException("Help Seeker not found");
        }
        HelpSeeker helpSeeker = helpSeekerOptional.get();
        Optional<List<Task>> allOpenTasksDtoOptional = taskService.getBookedTasksForAHelpSeeker(helpSeeker);
        if (allOpenTasksDtoOptional.isEmpty()){
            throw new NotFoundException("No open tasks found");
        }

        List<TaskResponseDto> ans = new ArrayList<>();

        for (Task task : allOpenTasksDtoOptional.get()) {
            ans.add(TaskUtility.convertTaskToTaskResponseDto(task));
        }
        AllOpenTasksDto allOpenTasksDto = new AllOpenTasksDto();
        allOpenTasksDto.setTasks(ans);
        return new ResponseEntity<>(allOpenTasksDto, HttpStatus.OK);
    }
    @GetMapping("/{helpSeekerId}")
    public ResponseEntity<HelpSeekerResponseDto> getHelpSeekerById(@PathVariable(name = "helpSeekerId") Long helpSeekerId) throws NotFoundException {
        Optional<HelpSeeker> helpSeekerOptional = helpSeekerService.getHelpSeekerById(helpSeekerId);
        if (helpSeekerOptional.isEmpty()){
            throw new NotFoundException("Help Seeker with id " + helpSeekerId + " not found");
        }
        HelpSeekerResponseDto helpSeekerResponseDto =
                HelpSeekerUtility.convertHelpSeekerToHelpSeekerResponseDto(helpSeekerOptional.get());
        return new ResponseEntity<>(helpSeekerResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HelpSeekerResponseDto>> getAllHelpSeekers() throws NotFoundException {
        Optional<List<HelpSeeker>> helpSeekerOptional = helpSeekerService.getAllHelpSeekers();
        if (helpSeekerOptional.isEmpty()){
            throw new NotFoundException("No help seekers found");
        }
        List<HelpSeekerResponseDto> helpSeekerResponseDtos = new ArrayList<>();
        for (HelpSeeker helpSeeker : helpSeekerOptional.get()) {
            helpSeekerResponseDtos.add(HelpSeekerUtility.convertHelpSeekerToHelpSeekerResponseDto(helpSeeker));
        }
        return new ResponseEntity<>(helpSeekerResponseDtos, HttpStatus.OK);
    }

    @PutMapping("/{helpSeekerId}")
    public ResponseEntity<HelpSeekerResponseDto> updateHelpSeekerById(
            @PathVariable(name = "helpSeekerId") Long helpSeekerId,
            @RequestBody HelpSeekerRequestDto helpSeekerRequestDto) throws NotFoundException {
        HelpSeeker helpSeeker = HelpSeekerUtility.convertHelpSeekerRequestDtoToHelpSeeker(helpSeekerRequestDto);
        Optional<HelpSeeker> helpSeekerOptional = helpSeekerService.updateHelpSeekerById(helpSeekerId, helpSeeker);
        if (helpSeekerOptional.isEmpty()){
            throw new NotFoundException("Help Seeker with id " + helpSeekerId + " not found");
        }

        HelpSeeker helpSeekerToUpdate = helpSeekerOptional.get();

        HelpSeekerResponseDto helpSeekerResponseDto =
                HelpSeekerUtility.convertHelpSeekerToHelpSeekerResponseDto(helpSeekerToUpdate);
        return new ResponseEntity<>(helpSeekerResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{helpSeekerId}")
    public ResponseEntity<HelpSeekerResponseDto> deleteHelpSeekerById(@PathVariable(name = "helpSeekerId") Long helpSeekerId) throws NotFoundException {
        Optional<HelpSeeker> helpSeekerOptional = helpSeekerService.deleteHelpSeekerById(helpSeekerId);
        if (helpSeekerOptional.isEmpty()){
            throw new NotFoundException("Help Seeker with id " + helpSeekerId + " not found");
        }
        HelpSeekerResponseDto helpSeekerResponseDto =
                HelpSeekerUtility.convertHelpSeekerToHelpSeekerResponseDto(helpSeekerOptional.get());
        return new ResponseEntity<>(helpSeekerResponseDto, HttpStatus.OK);
    }

}
