package com.example.help_with_my_tasks.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class HelpSeeker extends BaseModel{
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String phoneNumber;
    private String address;

    @OneToMany(fetch = jakarta.persistence.FetchType.EAGER,
            mappedBy = "helpSeeker",
            cascade = jakarta.persistence.CascadeType.REMOVE)
    private List<Task> tasks;
}
