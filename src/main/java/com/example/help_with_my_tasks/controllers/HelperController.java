package com.example.help_with_my_tasks.controllers;

import com.example.help_with_my_tasks.dtos.HelperRequestDto;
import com.example.help_with_my_tasks.dtos.HelperResponseDto;
import com.example.help_with_my_tasks.exceptions.NotFoundException;
import com.example.help_with_my_tasks.models.Helper;
import com.example.help_with_my_tasks.services.service_interfaces.HelperService;
import com.example.help_with_my_tasks.utilities.HelperUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/helper")
public class HelperController {

    private final HelperService helperService;

    public HelperController(HelperService helperService) {
        this.helperService = helperService;
    }

    @PostMapping
    public ResponseEntity<HelperResponseDto> createHelper(@RequestBody HelperRequestDto helperRequestDto)
            throws NotFoundException {
        Helper helper = HelperUtility.convertHelperRequsestDtoToHelper(helperRequestDto);
        Optional<Helper> optionalHelper = helperService.createHelper(helper);
        if (optionalHelper.isEmpty()){
            throw new NotFoundException("Helper not created");
        }
        HelperResponseDto helperResponseDto = HelperUtility.convertHelperToHelperResponseDto(optionalHelper.get());
        return new ResponseEntity<>(helperResponseDto, HttpStatus.OK);
    }



}
