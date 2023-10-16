package com.example.help_with_my_tasks.repositories;

import com.example.help_with_my_tasks.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<List<Booking>> findAllByHelperId(Long helperId);
}
