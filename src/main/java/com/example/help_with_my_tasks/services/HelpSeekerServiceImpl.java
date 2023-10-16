package com.example.help_with_my_tasks.services;

import com.example.help_with_my_tasks.models.HelpSeeker;
import com.example.help_with_my_tasks.repositories.HelpSeekerRepository;
import com.example.help_with_my_tasks.services.service_interfaces.HelpSeekerService;
import org.springframework.stereotype.Service;

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

}
