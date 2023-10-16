package com.example.help_with_my_tasks.controllers;

import com.example.help_with_my_tasks.dtos.BookingResponseDto;
import com.example.help_with_my_tasks.exceptions.NotFoundException;
import com.example.help_with_my_tasks.models.Booking;
import com.example.help_with_my_tasks.models.Helper;
import com.example.help_with_my_tasks.models.Task;
import com.example.help_with_my_tasks.services.service_interfaces.BookingService;
import com.example.help_with_my_tasks.services.service_interfaces.HelperService;
import com.example.help_with_my_tasks.services.service_interfaces.TaskService;
import com.example.help_with_my_tasks.utilities.BookingUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final HelperService helperService;
    private final TaskService taskService;

    public BookingController(BookingService bookingService, HelperService helperService, TaskService taskService) {
        this.bookingService = bookingService;
        this.helperService = helperService;
        this.taskService = taskService;
    }
    /*
    Creating a booking when a helper accepts a task. Booking is accepted, Helper is unavailable, Task is booked.
    */
    @PostMapping("{helperId}/{taskId}")
    public ResponseEntity<BookingResponseDto> createBooking(@PathVariable Long helperId,
                                                            @PathVariable Long taskId) throws NotFoundException {
//        Booking booking = BookingUtility.convertBookingRequestDtoToBooking(bookingRequestDto);
        Booking booking = new Booking();
//        booking.setBookingStatus(BookingStatus.ACCEPTED);
        Optional<Helper> helperOptional = helperService.getHelperById(helperId);
        if (helperOptional.isEmpty()){
            throw new NotFoundException("Helper not found");
        }
        Optional<Task> taskOptional = taskService.getTaskById(taskId);
        if (taskOptional.isEmpty()){
            throw new NotFoundException("Task not found");
        }
        Helper helper = helperOptional.get();
//        helper.setHelperStatus(HelperStatus.UNAVAILABLE);
        Task task = taskOptional.get();
//        task.setTaskStatus(TaskStatus.BOOKED);
        Optional<Booking> bookingOptional = bookingService.createBooking(booking, helper, task);
        if (bookingOptional.isEmpty()){
            throw new NotFoundException("Booking not found");
        }
        Booking bookingCreated = bookingOptional.get();
        BookingResponseDto bookingResponseDto = BookingUtility.convertBookingToBookingResponseDto(bookingCreated);
        return new ResponseEntity<>(bookingResponseDto, HttpStatus.OK);
    }

    @PatchMapping("{bookingId}")
    public ResponseEntity<BookingResponseDto> endBooking(@PathVariable(name = "bookingId") Long bookingId) throws NotFoundException {
        Optional<Booking> bookingOptional = bookingService.endBooking(bookingId);
        if (bookingOptional.isEmpty()){
            throw new NotFoundException("Booking not found");
        }
        Booking booking = bookingOptional.get();
//        booking.setBookingStatus(BookingStatus.COMPLETED);
        return new ResponseEntity<>(BookingUtility.convertBookingToBookingResponseDto(booking), HttpStatus.OK);
    }

}
