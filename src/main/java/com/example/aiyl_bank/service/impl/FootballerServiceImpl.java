package com.example.aiyl_bank.service.impl;

import com.example.aiyl_bank.dto.FootballerRequest;
import com.example.aiyl_bank.dto.FootballerResponse;
import com.example.aiyl_bank.enums.AscOrDesc;
import com.example.aiyl_bank.exceptions.BadCredentialException;
import com.example.aiyl_bank.exceptions.NotFoundException;
import com.example.aiyl_bank.model.Club;
import com.example.aiyl_bank.model.Footballer;
import com.example.aiyl_bank.service.FootballerService;
import com.example.aiyl_bank.service.rep.ClubRepo;
import com.example.aiyl_bank.service.rep.FootballerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FootballerServiceImpl implements FootballerService {
    private final FootballerRepo footballerRepo;
    private final ClubRepo clubRepo;

    @Override
    public String addFootballerToClubById(Long clubId, FootballerRequest footballerRequest) {
        String clubMessage = String.format(" %s IDдеги клуб табылган жок !!!", clubId);
        Club club = clubRepo.findById(clubId).orElseThrow(
                ()->new NotFoundException(clubMessage));
        Footballer footballer=new Footballer();
        footballer.setFirstName(footballerRequest.getFirstName());
        footballer.setEmail(footballerRequest.getEmail());
        footballer.setPrice(footballerRequest.getPrice());
        footballer.setPassword(footballerRequest.getPassword());
        footballer.setClub(club);
        Footballer footballerSaved = footballerRepo.save(footballer);
        return String.format("%s ысымдуу футболист %s клубуна ийгиликтуу келди .",
                footballerSaved.getFirstName(),club.getName());
    }

    @Override
    public List<FootballerResponse> getAllFootballersByAscOrDescPrice(AscOrDesc ascOrDesc) {
        if(ascOrDesc.equals(AscOrDesc.ASC)){
            return footballerRepo.getAllFootballersByAscPrice();
        } else if (ascOrDesc.equals(AscOrDesc.DESC)) {
            return footballerRepo.getAllFootballersByDescPrice();
        }else {
            throw new BadCredentialException("Туура танданыз Asc же Desc !!!");
        }
    }

    @Override
    public FootballerResponse getFootballerByEmail(String email) {
        String footballerMessage = String.format("%s EMAIL дагы футболист  табылган жок !!!",email);
        Footballer footballer = footballerRepo.getFootballerByEmail(email).orElseThrow(
                () -> new NotFoundException(footballerMessage));
      return FootballerResponse.build(footballer);
    }

    @Override
    public String updateFootballerByIdAndClubId(Long id, Long clubId, FootballerRequest footballerRequest) {
        String footballerMessage = String.format("%s ID деги футболист %s ID деги клубта  табылган жок !!!",id,clubId);
        String clubMessage = String.format(" %s IDдеги клуб табылган жок !!!", id);
        Footballer footballer = footballerRepo.getByIdAndClubId(id, clubId).orElseThrow(
                () -> new NotFoundException(footballerMessage));
        Club club = clubRepo.findById(clubId).orElseThrow(
                () -> new NotFoundException(clubMessage));
        footballer.setClub(club);
        footballer.setFirstName(footballerRequest.getFirstName());
        footballer.setEmail(footballerRequest.getEmail());
        footballer.setPrice(footballerRequest.getPrice());
        footballer.setPassword(footballerRequest.getPassword());
        footballerRepo.save(footballer);
        return String.format("%s клубунун футболисти %s ийгиликтуу озгортулду .",
                club.getName(),footballer.getFirstName());
    }

    @Override
    public String deleteFootballerByIdAndClubById(Long id, Long clubId) {
        String footballerMessage = String.format("%s ID деги футболист %s ID деги клубта  табылган жок !!!",id,clubId);
        Footballer footballer = footballerRepo.getByIdAndClubId(id, clubId).orElseThrow(
                () -> new NotFoundException(footballerMessage));
        footballerRepo.delete(footballer);
        return String.format("%s ID деги футболист ийгиликтуу очурулду .",id);
    }
}