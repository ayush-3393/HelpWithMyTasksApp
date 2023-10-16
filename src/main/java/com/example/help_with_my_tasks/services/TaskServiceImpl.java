package com.example.help_with_my_tasks.services;

import com.example.help_with_my_tasks.models.HelpSeeker;
import com.example.help_with_my_tasks.models.Task;
import com.example.help_with_my_tasks.models.enums.TaskStatus;
import com.example.help_with_my_tasks.repositories.TaskRepository;
import com.example.help_with_my_tasks.services.service_interfaces.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> createTask(Task task, HelpSeeker helpSeeker) {
        if (task == null){
            return Optional.empty();
        }
        task.setHelpSeeker(helpSeeker);
        task.setCreatedAt(new Date());
        return Optional.of(taskRepository.save(task));
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        if (id == null){
            return Optional.empty();
        }
        return taskRepository.findById(id);
    }

    @Override
    public Optional<List<Task>> getOpenTasksForAHelpSeeker(HelpSeeker helpSeeker) {
        if (helpSeeker == null){
            return Optional.empty();
        }
        return taskRepository.findAllByHelpSeekerAndTaskStatus(helpSeeker, TaskStatus.OPEN);
    }
}
