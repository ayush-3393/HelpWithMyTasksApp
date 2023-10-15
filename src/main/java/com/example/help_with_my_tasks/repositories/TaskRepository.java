package com.example.help_with_my_tasks.repositories;

import com.example.help_with_my_tasks.models.HelpSeeker;
import com.example.help_with_my_tasks.models.Task;
import com.example.help_with_my_tasks.models.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<List<Task>> findAllByHelpSeekerAndTaskStatus(HelpSeeker helpSeeker, TaskStatus status);
}
