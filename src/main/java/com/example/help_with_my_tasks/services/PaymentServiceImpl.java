package com.example.help_with_my_tasks.services;

import com.example.help_with_my_tasks.models.Booking;
import com.example.help_with_my_tasks.models.HelpSeeker;
import com.example.help_with_my_tasks.models.Payment;
import com.example.help_with_my_tasks.models.Task;
import com.example.help_with_my_tasks.models.enums.PaymentStatus;
import com.example.help_with_my_tasks.models.enums.TaskStatus;
import com.example.help_with_my_tasks.repositories.PaymentRepository;
import com.example.help_with_my_tasks.services.service_interfaces.PaymentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> createPayment(Booking booking) {
        booking.getTask().setTaskStatus(TaskStatus.COMPLETED);
        Payment payment = new Payment();
        payment.setAmount(booking.getAmount());
        payment.setBooking(booking);
        payment.setPaymentDate(new Date());
        payment.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        paymentRepository.save(payment);
        return Optional.of(payment);
    }
}
