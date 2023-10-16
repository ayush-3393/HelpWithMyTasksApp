package com.example.help_with_my_tasks.services;

import com.example.help_with_my_tasks.models.HelpSeeker;
import com.example.help_with_my_tasks.repositories.HelpSeekerRepository;
import com.example.help_with_my_tasks.services.service_interfaces.HelpSeekerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HelpSeekerServiceImpl implements HelpSeekerService {

    private final HelpSeekerRepository helpSeekerRepository;

    public HelpSeekerServiceImpl(HelpSeekerRepository helpSeekerRepository) {
        this.helpSeekerRepository = helpSeekerRepository;
    }

    @Override
    public Optional<HelpSeeker> createHelpSeeker(HelpSeeker helpSeeker) {
        if (helpSeeker == null) {
            return Optional.empty();
        }
        helpSeeker.setCreatedAt(new Date());
        return Optional.of(helpSeekerRepository.save(helpSeeker));
    }

    @Override
    public Optional<HelpSeeker> getHelpSeekerById(Long helpSeekerId) {
        return helpSeekerRepository.findById(helpSeekerId);
    }

    @Override
    public Optional<List<HelpSeeker>> getAllHelpSeekers() {
        return Optional.of(helpSeekerRepository.findAll());
    }

    @Override
    public Optional<HelpSeeker> updateHelpSeekerById(Long helpSeekerId, HelpSeeker helpSeeker) {
        Optional<HelpSeeker> helpSeekerOptional = helpSeekerRepository.findById(helpSeekerId);
        if (helpSeekerOptional.isEmpty()) {
            return Optional.empty();
        }
        HelpSeeker helpSeekerToUpdate = helpSeekerOptional.get();
        helpSeekerToUpdate.setFirstName(helpSeeker.getFirstName());
        helpSeekerToUpdate.setLastName(helpSeeker.getLastName());
        helpSeekerToUpdate.setGender(helpSeeker.getGender());
        helpSeekerToUpdate.setEmail(helpSeeker.getEmail());
        helpSeekerToUpdate.setAge(helpSeeker.getAge());
        helpSeekerToUpdate.setPhoneNumber(helpSeeker.getPhoneNumber());
        helpSeekerToUpdate.setAddress(helpSeeker.getAddress());
        helpSeekerToUpdate.setUpdatedAt(new Date());
        return Optional.of(helpSeekerRepository.save(helpSeekerToUpdate));
    }

    @Override
    public Optional<HelpSeeker> deleteHelpSeekerById(Long helpSeekerId) {
        Optional<HelpSeeker> helpSeekerOptional = helpSeekerRepository.findById(helpSeekerId);
        if (helpSeekerOptional.isEmpty()) {
            return Optional.empty();
        }
        HelpSeeker helpSeekerToDelete = helpSeekerOptional.get();
        helpSeekerToDelete.setDeleted(true);
        return Optional.of(helpSeekerRepository.save(helpSeekerToDelete));
    }

}
