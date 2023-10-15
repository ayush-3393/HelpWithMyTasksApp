package com.example.help_with_my_tasks.dtos;

import com.example.help_with_my_tasks.models.enums.HelperStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class HelperResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String phoneNumber;
    private String address;
    private HelperStatus helperStatus;
    private List<BookingResponseDto> bookings;
}
