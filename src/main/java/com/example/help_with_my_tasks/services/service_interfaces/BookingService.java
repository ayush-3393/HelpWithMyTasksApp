package com.example.help_with_my_tasks.services.service_interfaces;

import com.example.help_with_my_tasks.models.*;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Optional<Booking> createBooking(Booking booking, Helper helper, Task task);
    Optional<Booking> endBooking(Long bookingId, Rating rating);
    Optional<List<Booking>> getAllBookingsForAHelper(Helper helper);

    void sendNotification(Notification notification);
}
