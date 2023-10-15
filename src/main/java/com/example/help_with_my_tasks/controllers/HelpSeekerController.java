package com.example.help_with_my_tasks.controllers;

import com.example.help_with_my_tasks.dtos.HelpSeekerRequestDto;
import com.example.help_with_my_tasks.dtos.HelpSeekerResponseDto;
import com.example.help_with_my_tasks.exceptions.NotFoundException;
import com.example.help_with_my_tasks.models.HelpSeeker;
import com.example.help_with_my_tasks.services.service_interfaces.HelpSeekerService;
import com.example.help_with_my_tasks.utilities.HelpSeekerUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/helpseeker")
public class HelpSeekerController {

    private final HelpSeekerService helpSeekerService;

    public HelpSeekerController(HelpSeekerService helpSeekerService) {
        this.helpSeekerService = helpSeekerService;
    }

    @PostMapping
    public ResponseEntity<HelpSeekerResponseDto> createHelpSeeker
            (@RequestBody HelpSeekerRequestDto helpSeekerRequestDto) throws NotFoundException {
        HelpSeeker helpSeeker = HelpSeekerUtility.convertHelpSeekerRequestDtoToHelpSeeker(helpSeekerRequestDto);
        Optional<HelpSeeker> optionalHelpSeeker = helpSeekerService.createHelpSeeker(helpSeeker);
        if (optionalHelpSeeker.isEmpty()){
            throw new NotFoundException("Help Seeker not found");
        }
        HelpSeekerResponseDto helpSeekerResponseDto =
                HelpSeekerUtility.convertHelpSeekerToHelpSeekerResponseDto(optionalHelpSeeker.get());
        return new ResponseEntity<>(helpSeekerResponseDto, HttpStatus.OK);
    }

}
