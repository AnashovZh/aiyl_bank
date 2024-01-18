package com.example.aiyl_bank.service.impl;

import com.example.aiyl_bank.dto.CountryRequest;
import com.example.aiyl_bank.dto.CountryResponse;
import com.example.aiyl_bank.exceptions.NotFoundException;
import com.example.aiyl_bank.model.Country;
import com.example.aiyl_bank.service.CountryService;
import com.example.aiyl_bank.service.rep.CountryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepo countryRepo;

    @Override
    public String addCountry(CountryRequest country) {
        Country country1=new Country();
        country1.setName(country.getName());
        country1.setPopulation(country.getPopulation());
        countryRepo.save(country1);
        return "Ийгиликтуу олко кошулду .";
    }

    @Override
    public List<CountryResponse> getAllCountries() {
        return countryRepo.getAllCountries();
    }

    @Override
    public String updateCountryById(Long id, CountryRequest countryRequest) {
        Country country = countryRepo.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Бул %s ID дe олко жок !!!",id)));
        country.setName(countryRequest.getName());
        country.setPopulation(countryRequest.getPopulation());
        countryRepo.save(country);
        return "Олко ийгиликтуу жаныланды ";
    }

    @Override
    public String deleteCountryById(Long id) {
        String format = String.format("Бул %s - ID де олко жок", id);
        Country country = countryRepo.findById(id).orElseThrow(
                () -> new NotFoundException(format));
        countryRepo.delete(country);
        return String.format("Ушул ID= %s деги олко ийгиликтуу очурулду ",id);
    }
}