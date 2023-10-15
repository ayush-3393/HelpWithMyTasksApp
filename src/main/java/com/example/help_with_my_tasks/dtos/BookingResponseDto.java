package com.example.help_with_my_tasks.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BookingResponseDto {
    private Long id;
    private Integer amount;
    private Date bookingDate;
    private String helperName;
    private String taskName;
    private String helpSeekerName;
}
