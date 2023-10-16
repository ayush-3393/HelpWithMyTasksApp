package com.example.help_with_my_tasks.utilities;

import com.example.help_with_my_tasks.dtos.BookingResponseDto;
import com.example.help_with_my_tasks.models.Booking;
import com.example.help_with_my_tasks.models.HelpSeeker;
import com.example.help_with_my_tasks.models.Helper;
import com.example.help_with_my_tasks.models.Task;

public class BookingUtility {

//    public static Booking convertBookingRequestDtoToBooking(BookingRequestDto bookingRequestDto){
//        Booking booking = new Booking();
//        booking.setAmount(bookingRequestDto.getAmount());
//        return booking;
//    }

    public static BookingResponseDto convertBookingToBookingResponseDto(Booking booking){
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setId(booking.getId());
        bookingResponseDto.setAmount(booking.getAmount());
        bookingResponseDto.setBookingDate(booking.getBookingDate());
        Task task = booking.getTask();
        bookingResponseDto.setTaskName(task.getTaskTitle());
        Helper helper = booking.getHelper();
        bookingResponseDto.setHelperName(helper.getFirstName() + " " + helper.getLastName());
        HelpSeeker helpSeeker = task.getHelpSeeker();
        bookingResponseDto.setHelpSeekerName(helpSeeker.getFirstName() + " " + helpSeeker.getLastName());
        bookingResponseDto.setBookingStatus(booking.getBookingStatus());
        return bookingResponseDto;
    }

}
