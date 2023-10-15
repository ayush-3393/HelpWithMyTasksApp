package com.example.help_with_my_tasks.models;

import com.example.help_with_my_tasks.models.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Payment extends BaseModel{
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private Date paymentDate;

    @OneToOne
    private Booking booking;
}
