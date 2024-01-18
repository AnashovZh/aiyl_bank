package com.example.aiyl_bank.service;

import com.example.aiyl_bank.dto.FootballerRequest;
import com.example.aiyl_bank.dto.FootballerResponse;
import com.example.aiyl_bank.enums.AscOrDesc;
import java.util.List;

public interface FootballerService {
    String addFootballerToClubById(Long clubId, FootballerRequest footballerRequest);
    List<FootballerResponse> getAllFootballersByAscOrDescPrice(AscOrDesc ascOrDesc);
    String updateFootballerByIdAndClubId(Long id, Long clubId, FootballerRequest footballerRequest);
    String deleteFootballerByIdAndClubById(Long id, Long clubId);
    FootballerResponse getFootballerByEmail(String email);
}