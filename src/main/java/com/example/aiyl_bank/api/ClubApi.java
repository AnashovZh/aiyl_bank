package com.example.aiyl_bank.api;

import com.example.aiyl_bank.dto.ClubRequest;
import com.example.aiyl_bank.dto.ClubResponse;
import com.example.aiyl_bank.service.ClubService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/clubs")
@RequiredArgsConstructor
@Tag(name = "ClubApi")
public class ClubApi {
    private final ClubService clubService;

    @PostMapping
    String addClubToCountry(@RequestParam Long countryId,
                            @RequestBody ClubRequest clubRequest){
        return clubService.addClubToCountry(countryId,clubRequest);
    }

    @GetMapping
    ClubResponse getById(@RequestParam Long id){
        return clubService.getById(id);
    }

    @GetMapping("/getClubsByCountryId")
    List<ClubResponse>getClubsByCountryId(@RequestParam Long countryId ){
        return clubService.getClubsByCountryId(countryId);
    }

    @PutMapping()
    String updateClubByIdAndCountryId(@RequestParam Long id,
                                      @RequestParam Long countryId,
                                      @RequestBody ClubRequest clubRequest){
        return clubService.updateClubByIdAndCountryId(id,countryId,clubRequest);
    }

    @DeleteMapping
    String deleteByIdAndCountryId(@RequestParam Long id,
                                  @RequestParam Long countryId){
        return clubService.deleteByIdAndCountryId(id,countryId);
    }
}