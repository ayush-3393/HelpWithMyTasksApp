package com.example.help_with_my_tasks.dtos;

import com.example.help_with_my_tasks.models.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class HelpSeekerResponseDto {
    private Long helpSeekerId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private Integer age;
    private String phoneNumber;
    private String address;
    private Double rating;
}
