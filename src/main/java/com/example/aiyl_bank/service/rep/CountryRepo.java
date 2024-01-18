package com.example.aiyl_bank.service.rep;

import com.example.aiyl_bank.dto.CountryResponse;
import com.example.aiyl_bank.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CountryRepo extends JpaRepository<Country,Long> {
@Query("select new com.example.aiyl_bank.dto." +
        "CountryResponse (c.id,c.name,c.population) from Country c ")
    List<CountryResponse>getAllCountries() ;
}