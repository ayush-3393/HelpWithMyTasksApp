package com.example.help_with_my_tasks.services;

import com.example.help_with_my_tasks.models.Helper;
import com.example.help_with_my_tasks.repositories.HelperRepository;
import com.example.help_with_my_tasks.services.service_interfaces.HelperService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class HelperServiceImpl implements HelperService {

    private final HelperRepository helperRepository;

    public HelperServiceImpl(HelperRepository helperRepository) {
        this.helperRepository = helperRepository;
    }

    @Override
    public Optional<Helper> createHelper(Helper helper) {
        if (helper == null) {
            return Optional.empty();
        }
        helper.setCreatedAt(new Date());
        return Optional.of(helperRepository.save(helper));
    }

    @Override
    public Optional<Helper> getHelperById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return helperRepository.findById(id);
    }
}
