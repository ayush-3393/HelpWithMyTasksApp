package com.example.help_with_my_tasks.services;

import com.example.help_with_my_tasks.models.Booking;
import com.example.help_with_my_tasks.models.Helper;
import com.example.help_with_my_tasks.models.Task;
import com.example.help_with_my_tasks.repositories.BookingRepository;
import com.example.help_with_my_tasks.services.service_interfaces.BookingService;
import com.example.help_with_my_tasks.services.service_interfaces.HelperService;
import com.example.help_with_my_tasks.services.service_interfaces.TaskService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Optional<Booking> createBooking(Booking booking, Helper helper, Task task) {
        if (booking == null){
            return Optional.empty();
        }
        booking.setHelper(helper);
        booking.setTask(task);
        return Optional.of(bookingRepository.save(booking));
    }
}
