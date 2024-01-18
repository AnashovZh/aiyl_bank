package com.example.aiyl_bank.api;

import com.example.aiyl_bank.dto.CountryRequest;
import com.example.aiyl_bank.dto.CountryResponse;
import com.example.aiyl_bank.service.CountryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/countries")
@RequiredArgsConstructor
@Tag(name = "CountryApi")
public class CountryApi {
    private final CountryService countryService;

    @PostMapping
    String addCountry(@RequestBody CountryRequest country){
        return countryService.addCountry(country);
    }

    @GetMapping
    List<CountryResponse> getAllCountries(){
        return countryService.getAllCountries();
    }

    @PutMapping
    String updateCountryById(@RequestParam Long id,@RequestBody CountryRequest countryRequest){
        return countryService.updateCountryById(id,countryRequest);
    }

    @DeleteMapping
    String deleteCountryById(@RequestParam Long id){
        return countryService.deleteCountryById(id);
    }
}