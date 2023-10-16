package com.example.help_with_my_tasks.services;

import com.example.help_with_my_tasks.models.Helper;
import com.example.help_with_my_tasks.repositories.HelperRepository;
import com.example.help_with_my_tasks.services.service_interfaces.HelperService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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

    @Override
    public Optional<List<Helper>> getAllHelpers() {
        return Optional.of(helperRepository.findAll());
    }

    @Override
    public Optional<Helper> updateHelperById(Long id, Helper helper) {
        if(id == null || helper == null){
            return Optional.empty();
        }
        Optional<Helper> helperOptional = helperRepository.findById(id);
        if(helperOptional.isEmpty()){
            return Optional.empty();
        }
        Helper helperToUpdate = helperOptional.get();
        helperToUpdate.setFirstName(helper.getFirstName());
        helperToUpdate.setLastName(helper.getLastName());
        helperToUpdate.setGender(helper.getGender());
        helperToUpdate.setEmail(helper.getEmail());
        helperToUpdate.setAge(helper.getAge());
        helperToUpdate.setPhoneNumber(helper.getPhoneNumber());
        helperToUpdate.setAddress(helper.getAddress());
        helperToUpdate.setUpdatedAt(new Date());
        helperToUpdate.setHelperStatus(helper.getHelperStatus());
        return Optional.of(helperRepository.save(helperToUpdate));
    }

    @Override
    public Optional<Helper> deleteHelperById(Long id) {
        if(id == null){
            return Optional.empty();
        }
        Optional<Helper> helperOptional = helperRepository.findById(id);
        if(helperOptional.isEmpty()){
            return Optional.empty();
        }
        Helper helperToDelete = helperOptional.get();
        helperToDelete.setDeleted(true);
        return Optional.of(helperRepository.save(helperToDelete));
    }
}
