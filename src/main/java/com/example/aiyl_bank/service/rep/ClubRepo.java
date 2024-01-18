package com.example.aiyl_bank.service.rep;

import com.example.aiyl_bank.dto.ClubResponse;
import com.example.aiyl_bank.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepo extends JpaRepository<Club, Long> {
    @Query("select new com.example.aiyl_bank.dto.ClubResponse (cl.id,cl.name,cl.price)" +
            "from Club cl join Country c on cl.country.id=c.id where c.id=?1 ")
    List<ClubResponse> getClubsByCountryId(Long countryId);

    @Query("select new com.example.aiyl_bank.model.Club (cl.id,cl.name,cl.price)" +
            "from Club cl join Country c on cl.country.id=c.id where cl.id=?1 and c.id=?2")
    Optional<Club> getByIdAndCountryId(Long id, Long countryId);
}