package com.example.help_with_my_tasks.services.service_interfaces;

import com.example.help_with_my_tasks.models.HelpSeeker;

import java.util.List;
import java.util.Optional;

public interface HelpSeekerService {
    Optional<HelpSeeker> createHelpSeeker(HelpSeeker helpSeeker);
    Optional<HelpSeeker> getHelpSeekerById(Long helpSeekerId);
    Optional<List<HelpSeeker>> getAllHelpSeekers();
    Optional<HelpSeeker> updateHelpSeekerById(Long helpSeekerId, HelpSeeker helpSeeker);

}
