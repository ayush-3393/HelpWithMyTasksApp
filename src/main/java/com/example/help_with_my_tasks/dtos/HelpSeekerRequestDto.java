package com.example.help_with_my_tasks.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HelpSeekerRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String phoneNumber;
    private String address;
}
