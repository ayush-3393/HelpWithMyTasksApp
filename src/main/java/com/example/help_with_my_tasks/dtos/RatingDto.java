package com.example.help_with_my_tasks.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RatingDto {
    private Integer ratingFromHelpSeekerToHelper;
    private Integer ratingFromHelperToHelpSeeker;
}
