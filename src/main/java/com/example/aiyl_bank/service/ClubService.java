package com.example.aiyl_bank.service;

import com.example.aiyl_bank.dto.ClubRequest;
import com.example.aiyl_bank.dto.ClubResponse;
import java.util.List;

public interface ClubService {
    String addClubToCountry(Long countryId, ClubRequest clubRequest);
    ClubResponse getById(Long id);
    List<ClubResponse> getClubsByCountryId(Long countryId);
    String updateClubByIdAndCountryId(Long id, Long countryId, ClubRequest clubRequest);
    String deleteByIdAndCountryId(Long id, Long countryId);
}