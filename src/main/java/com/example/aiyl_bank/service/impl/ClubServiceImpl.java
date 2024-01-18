package com.example.aiyl_bank.service.impl;

import com.example.aiyl_bank.dto.ClubRequest;
import com.example.aiyl_bank.dto.ClubResponse;
import com.example.aiyl_bank.exceptions.NotFoundException;
import com.example.aiyl_bank.model.Club;
import com.example.aiyl_bank.model.Country;
import com.example.aiyl_bank.service.ClubService;
import com.example.aiyl_bank.service.rep.ClubRepo;
import com.example.aiyl_bank.service.rep.CountryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClubServiceImpl implements ClubService {
    private final CountryRepo countryRepo;
    private final ClubRepo clubRepo;

    @Override
    public String addClubToCountry(Long countryId, ClubRequest clubRequest) {
        log.info("Метод addClubToCountry ийгиликтуу иштеди .");
        Country country = countryRepo.findById(countryId).orElseThrow(
                () -> new NotFoundException("Ушул %s IDдеги олко жок !!!"));
        Club clubBuild = Club.builder()
                .name(clubRequest.getName())
                .price(clubRequest.getPrice())
                .build();
        clubBuild.setCountry(country);
        Club clubSave = clubRepo.save(clubBuild);
        log.info("Метод addClubToCountry ийгиликтуу жыйынтыкталды .");
        return String.format("Ушул %s ID деги олкого ушул %s ысымдагы клуб кошулду . ",countryId,clubSave.getName());
    }

    @Override
    public ClubResponse getById(Long id) {
        String format = String.format(" %s IDдеги клуб табылган жок !!!", id);
        Club club = clubRepo.findById(id).orElseThrow(
                () -> new NotFoundException(format));
       return ClubResponse.builder()
                .id(club.getId())
                .name(club.getName())
                .price(club.getPrice())
                .build();
    }

    @Override
    public List<ClubResponse> getClubsByCountryId(Long countryId) {
       return clubRepo.getClubsByCountryId(countryId);
    }

    @Override
    public String updateClubByIdAndCountryId(Long id, Long countryId, ClubRequest clubRequest) {
        String clubMessage = String.format(" %s IDдеги клуб табылган жок !!!", id);
        String countryMessage = String.format("Бул %s - ID де олко жок", id);
        Country country = countryRepo.findById(countryId).orElseThrow(
                () -> new NotFoundException(countryMessage));
        Club club = clubRepo.getByIdAndCountryId(id, countryId).orElseThrow(
                () -> new NotFoundException(clubMessage));
        club.setCountry(country);
        club.setName(clubRequest.getName());
        club.setPrice(clubRequest.getPrice());
         clubRepo.save(club);
        return String.format("%s ID деги олконун %s аттуу клубу ийгиликтуу озгорду.",countryId,club.getName());
    }

    @Override
    public String deleteByIdAndCountryId(Long id, Long countryId) {
        String clubMessage = String.format(" %s IDдеги клуб табылган жок !!!", id);
        Club club = clubRepo.getByIdAndCountryId(id, countryId).orElseThrow(
                () -> new NotFoundException(clubMessage));
        clubRepo.delete(club);
        return String.format( "%s IDдеги клуб ийгиликтуу очурулду .",id);
    }
}