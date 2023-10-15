package com.example.help_with_my_tasks.repositories;

import com.example.help_with_my_tasks.models.Helper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelperRepository extends JpaRepository<Helper, Long> {
}
