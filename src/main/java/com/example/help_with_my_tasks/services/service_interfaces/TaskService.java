package com.example.help_with_my_tasks.services.service_interfaces;

import com.example.help_with_my_tasks.models.HelpSeeker;
import com.example.help_with_my_tasks.models.Task;

import java.util.Optional;

public interface TaskService {
    Optional<Task> createTask(Task task, HelpSeeker helpSeeker);
    Optional<Task> getTaskById(Long id);
}
