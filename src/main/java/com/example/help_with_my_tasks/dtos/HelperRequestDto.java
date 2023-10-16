package com.example.help_with_my_tasks.dtos;

import com.example.help_with_my_tasks.models.enums.Gender;
import com.example.help_with_my_tasks.models.enums.HelperStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HelperRequestDto {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private Integer age;
    private String phoneNumber;
    private String address;
    private HelperStatus helperStatus;
}
