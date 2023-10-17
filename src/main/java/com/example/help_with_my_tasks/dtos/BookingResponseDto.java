package com.example.help_with_my_tasks.dtos;

import com.example.help_with_my_tasks.models.enums.BookingStatus;
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
    private BookingStatus bookingStatus;
    private Integer ratingFromHelpSeekerToHelper;
    private Integer ratingFromHelperToHelpSeeker;
}
