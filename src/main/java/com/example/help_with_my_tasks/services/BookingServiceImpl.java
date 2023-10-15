package com.example.help_with_my_tasks.services;

import com.example.help_with_my_tasks.models.Booking;
import com.example.help_with_my_tasks.models.Helper;
import com.example.help_with_my_tasks.models.Task;
import com.example.help_with_my_tasks.repositories.BookingRepository;
import com.example.help_with_my_tasks.services.service_interfaces.BookingService;
import com.example.help_with_my_tasks.services.service_interfaces.PaymentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PaymentService paymentService;

    public BookingServiceImpl(BookingRepository bookingRepository, PaymentService paymentService) {
        this.bookingRepository = bookingRepository;
        this.paymentService = paymentService;
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

    @Override
    public Optional<Booking> endBooking(Long bookingId) {
        if (bookingId == null){
            return Optional.empty();
        }
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isEmpty()){
            return Optional.empty();
        }
        Booking booking = bookingOptional.get();
        paymentService.createPayment(booking);
        return Optional.of(bookingRepository.save(booking));
    }

}
