package com.example.help_with_my_tasks.controllers;

import com.example.help_with_my_tasks.dtos.BookingResponseDto;
import com.example.help_with_my_tasks.dtos.HelperRequestDto;
import com.example.help_with_my_tasks.dtos.HelperResponseDto;
import com.example.help_with_my_tasks.exceptions.NotFoundException;
import com.example.help_with_my_tasks.models.Booking;
import com.example.help_with_my_tasks.models.Helper;
import com.example.help_with_my_tasks.services.service_interfaces.BookingService;
import com.example.help_with_my_tasks.services.service_interfaces.HelperService;
import com.example.help_with_my_tasks.utilities.BookingUtility;
import com.example.help_with_my_tasks.utilities.HelperUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/helper")
public class HelperController {

    private final HelperService helperService;
    private final BookingService bookingService;

    public HelperController(HelperService helperService, BookingService bookingService) {
        this.helperService = helperService;
        this.bookingService = bookingService;
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

    @GetMapping("/{helperId}")
    public ResponseEntity<HelperResponseDto> getHelperById(@PathVariable Long helperId) throws NotFoundException {
        Optional<Helper> optionalHelper = helperService.getHelperById(helperId);
        if (optionalHelper.isEmpty()){
            throw new NotFoundException("Helper with id " + helperId + " not found");
        }
        HelperResponseDto helperResponseDto = HelperUtility.convertHelperToHelperResponseDto(optionalHelper.get());
        return new ResponseEntity<>(helperResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HelperResponseDto>> getAllHelpers() throws NotFoundException {
        Optional<List<Helper>> optionalHelper = helperService.getAllHelpers();
        if (optionalHelper.isEmpty()){
            throw new NotFoundException("No helpers found");
        }
        List<HelperResponseDto> helperResponseDtoList = new ArrayList<>();
        List<Helper> helperList = optionalHelper.get();
        for (Helper helper : helperList) {
            helperResponseDtoList.add(HelperUtility.convertHelperToHelperResponseDto(helper));
        }
        return new ResponseEntity<>(helperResponseDtoList, HttpStatus.OK);
    }

    @PutMapping("/{helperId}")
    public ResponseEntity<HelperResponseDto> updateHelperById(@PathVariable Long helperId,
                                                              @RequestBody HelperRequestDto helperRequestDto)
            throws NotFoundException {
        Helper helper = HelperUtility.convertHelperRequsestDtoToHelper(helperRequestDto);
        Optional<Helper> optionalHelper = helperService.updateHelperById(helperId, helper);
        if (optionalHelper.isEmpty()){
            throw new NotFoundException("Helper with id " + helperId + " not found");
        }
        HelperResponseDto helperResponseDto = HelperUtility.convertHelperToHelperResponseDto(optionalHelper.get());
        return new ResponseEntity<>(helperResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{helperId}")
    public ResponseEntity<HelperResponseDto> deleteHelperById(@PathVariable Long helperId) throws NotFoundException {
        Optional<Helper> optionalHelper = helperService.deleteHelperById(helperId);
        if (optionalHelper.isEmpty()){
            throw new NotFoundException("Helper with id " + helperId + " not found");
        }
        HelperResponseDto helperResponseDto = HelperUtility.convertHelperToHelperResponseDto(optionalHelper.get());
        return new ResponseEntity<>(helperResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{helperId}/bookings")
    public ResponseEntity<List<BookingResponseDto>> getAllBookingsByHelperId(@PathVariable(name = "helperId") Long helperId) throws NotFoundException {
        Optional<Helper> helperOptional = helperService.getHelperById(helperId);
        if (helperOptional.isEmpty()){
            throw new NotFoundException("Helper with id " + helperId + " not found");
        }
        Optional<List<Booking>> bookingOptional = bookingService.getAllBookingsForAHelper(helperOptional.get());
        if (bookingOptional.isEmpty()){
            throw new NotFoundException("No bookings found for helper with id " + helperId);
        }
        List<BookingResponseDto> bookingResponseDtos = new ArrayList<>();
        for (Booking booking : bookingOptional.get()) {
            bookingResponseDtos.add(BookingUtility.convertBookingToBookingResponseDto(booking));
        }
        return new ResponseEntity<>(bookingResponseDtos, HttpStatus.OK);
    }

}
