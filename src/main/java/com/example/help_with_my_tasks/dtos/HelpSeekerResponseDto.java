package com.example.help_with_my_tasks.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class HelpSeekerResponseDto {
    private Long helpSeekerId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String phoneNumber;
    private String address;
    private List<TaskResponseDto> tasks;
}
