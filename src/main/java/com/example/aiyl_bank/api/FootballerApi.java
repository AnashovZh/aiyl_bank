package com.example.aiyl_bank.api;

import com.example.aiyl_bank.dto.FootballerResponse;
import com.example.aiyl_bank.dto.FootballerRequest;
import com.example.aiyl_bank.enums.AscOrDesc;
import com.example.aiyl_bank.service.FootballerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/footballers")
@Tag(name = "FootballerApi")
public class FootballerApi {

    private final FootballerService footballerService;

    @PostMapping
    String addFootballerToClubById(@RequestParam Long clubId,
                                   @RequestBody FootballerRequest footballerRequest){
        return footballerService.addFootballerToClubById(clubId,footballerRequest);
    }

    @GetMapping
    List<FootballerResponse> getAllFootballersByAscOrDescPrice(@RequestParam AscOrDesc ascOrDesc){
        return footballerService.getAllFootballersByAscOrDescPrice(ascOrDesc);
    }

    @GetMapping("/email")
    FootballerResponse getFootballerByEmail(@RequestParam String email){
        return footballerService.getFootballerByEmail(email);
    }

    @PutMapping
    String updateFootballerByIdAndClubId(@RequestParam Long id,
                                         @RequestParam Long clubId,
                                         @RequestBody FootballerRequest footballerRequest){
        return footballerService.updateFootballerByIdAndClubId(id,clubId,footballerRequest);
    }

    @DeleteMapping
    String deleteFootballerByIdAndClubById(@RequestParam Long id,
                                           @RequestParam Long clubId){
        return footballerService.deleteFootballerByIdAndClubById(id,clubId);
    }
}