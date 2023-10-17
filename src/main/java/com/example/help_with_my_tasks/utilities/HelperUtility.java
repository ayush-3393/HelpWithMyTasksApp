package com.example.help_with_my_tasks.utilities;

import com.example.help_with_my_tasks.dtos.HelperRequestDto;
import com.example.help_with_my_tasks.dtos.HelperResponseDto;
import com.example.help_with_my_tasks.models.Helper;

public class HelperUtility {
    public static Helper convertHelperRequsestDtoToHelper(HelperRequestDto helperRequestDto){
        Helper helper = new Helper();
        helper.setFirstName(helperRequestDto.getFirstName());
        helper.setLastName(helperRequestDto.getLastName());
        helper.setGender(helperRequestDto.getGender());
        helper.setPhoneNumber(helperRequestDto.getPhoneNumber());
        helper.setAddress(helperRequestDto.getAddress());
        helper.setHelperStatus(helperRequestDto.getHelperStatus());
        helper.setAge(helperRequestDto.getAge());
        helper.setEmail(helperRequestDto.getEmail());
        return helper;
    }

    public static HelperResponseDto convertHelperToHelperResponseDto(Helper helper){
        HelperResponseDto helperResponseDto = new HelperResponseDto();
        helperResponseDto.setId(helper.getId());
        helperResponseDto.setFirstName(helper.getFirstName());
        helperResponseDto.setLastName(helper.getLastName());
        helperResponseDto.setGender(helper.getGender());
        helperResponseDto.setPhoneNumber(helper.getPhoneNumber());
        helperResponseDto.setAddress(helper.getAddress());
        helperResponseDto.setHelperStatus(helper.getHelperStatus());
        helperResponseDto.setAge(helper.getAge());
        helperResponseDto.setEmail(helper.getEmail());
        helperResponseDto.setRating(helper.getRating());
        return helperResponseDto;
    }

}
