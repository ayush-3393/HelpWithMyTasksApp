package com.example.help_with_my_tasks.services.service_interfaces;

import com.example.help_with_my_tasks.models.Booking;
import com.example.help_with_my_tasks.models.Helper;
import com.example.help_with_my_tasks.models.Task;

import java.util.Optional;

public interface BookingService {
    Optional<Booking> createBooking(Booking booking, Helper helper, Task task);
}
