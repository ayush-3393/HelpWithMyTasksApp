package com.example.help_with_my_tasks.services.service_interfaces;

import com.example.help_with_my_tasks.models.Booking;
import com.example.help_with_my_tasks.models.Payment;

import java.util.Optional;

public interface PaymentService {
    Optional<Payment> createPayment(Booking booking);
}
