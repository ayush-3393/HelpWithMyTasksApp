package com.example.help_with_my_tasks.services.service_interfaces;

import com.example.help_with_my_tasks.models.Helper;

import java.util.List;
import java.util.Optional;

public interface HelperService {
    Optional<Helper> createHelper(Helper helper);
    Optional<Helper> getHelperById(Long id);
    Optional<List<Helper>> getAllHelpers();
    Optional<Helper> updateHelperById(Long id, Helper helper);
    Optional<Helper> deleteHelperById(Long id);
}
