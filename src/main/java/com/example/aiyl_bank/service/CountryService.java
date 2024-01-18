package com.example.aiyl_bank.service;

import com.example.aiyl_bank.dto.CountryRequest;
import com.example.aiyl_bank.dto.CountryResponse;
import java.util.List;

public interface CountryService {
    String addCountry(CountryRequest country);
    List<CountryResponse> getAllCountries();
    String updateCountryById(Long id, CountryRequest countryRequest);
    String deleteCountryById(Long id);
}