package com.example.help_with_my_tasks.utilities;

import com.example.help_with_my_tasks.dtos.RatingDto;
import com.example.help_with_my_tasks.models.Rating;

public class RatingUtility {
    public static Rating convertRatingDtoToRating(RatingDto ratingDto){
        Rating rating = new Rating();
        rating.setRatingFromHelpSeekerToHelper(ratingDto.getRatingFromHelpSeekerToHelper());
        rating.setRatingFromHelperToHelpSeeker(ratingDto.getRatingFromHelperToHelpSeeker());
        return rating;
    }
}
