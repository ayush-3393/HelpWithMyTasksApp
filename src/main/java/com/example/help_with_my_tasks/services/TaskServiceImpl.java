package com.example.help_with_my_tasks.services;

import com.example.help_with_my_tasks.models.HelpSeeker;
import com.example.help_with_my_tasks.models.Task;
import com.example.help_with_my_tasks.repositories.TaskRepository;
import com.example.help_with_my_tasks.services.service_interfaces.TaskService;
import org.springframework.stereotype.Service;

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
        return Optional.of(taskRepository.save(task));
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        if (id == null){
            return Optional.empty();
        }
        return taskRepository.findById(id);
    }
}
