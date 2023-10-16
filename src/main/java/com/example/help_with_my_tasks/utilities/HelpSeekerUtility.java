package com.example.help_with_my_tasks.utilities;

import com.example.help_with_my_tasks.dtos.HelpSeekerRequestDto;
import com.example.help_with_my_tasks.dtos.HelpSeekerResponseDto;
import com.example.help_with_my_tasks.models.HelpSeeker;

public class HelpSeekerUtility {
    public static HelpSeeker convertHelpSeekerRequestDtoToHelpSeeker(HelpSeekerRequestDto helpSeekerRequestDto){
        HelpSeeker helpSeeker = new HelpSeeker();
        helpSeeker.setFirstName(helpSeekerRequestDto.getFirstName());
        helpSeeker.setLastName(helpSeekerRequestDto.getLastName());
        helpSeeker.setGender(helpSeekerRequestDto.getGender());
        helpSeeker.setPhoneNumber(helpSeekerRequestDto.getPhoneNumber());
        helpSeeker.setAddress(helpSeekerRequestDto.getAddress());
        helpSeeker.setAge(helpSeekerRequestDto.getAge());
        helpSeeker.setEmail(helpSeekerRequestDto.getEmail());
        return helpSeeker;
    }

    public static HelpSeekerResponseDto convertHelpSeekerToHelpSeekerResponseDto(HelpSeeker helpSeeker){
        HelpSeekerResponseDto helpSeekerResponseDto = new HelpSeekerResponseDto();
        helpSeekerResponseDto.setHelpSeekerId(helpSeeker.getId());
        helpSeekerResponseDto.setFirstName(helpSeeker.getFirstName());
        helpSeekerResponseDto.setLastName(helpSeeker.getLastName());
        helpSeekerResponseDto.setGender(helpSeeker.getGender());
        helpSeekerResponseDto.setPhoneNumber(helpSeeker.getPhoneNumber());
        helpSeekerResponseDto.setAddress(helpSeeker.getAddress());
        helpSeekerResponseDto.setAge(helpSeeker.getAge());
        helpSeekerResponseDto.setEmail(helpSeeker.getEmail());
        return helpSeekerResponseDto;
    }

}
