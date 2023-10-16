package com.example.help_with_my_tasks.models;

import com.example.help_with_my_tasks.models.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class Booking extends BaseModel{

    @ManyToOne
    private Helper helper;

    @OneToOne
    Task task;

    private Integer amount;

    private Date bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}
