package com.example.help_with_my_tasks.models;

import com.example.help_with_my_tasks.models.enums.Gender;
import com.example.help_with_my_tasks.models.interfaces.RatingProvider;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class HelpSeeker extends BaseModel implements RatingProvider {
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;
    private Integer age;
    private String phoneNumber;
    private String address;

    @OneToMany(fetch = jakarta.persistence.FetchType.EAGER,
            mappedBy = "helpSeeker",
            cascade = jakarta.persistence.CascadeType.REMOVE)
    private List<Task> tasks;

    private Double rating;
    private Integer countOfTasksCompleted;

    @Override
    public void updateRating(Integer newRating) {
        this.rating = ((this.rating * (this.countOfTasksCompleted)) + newRating) / (this.countOfTasksCompleted + 1);
    }
}
