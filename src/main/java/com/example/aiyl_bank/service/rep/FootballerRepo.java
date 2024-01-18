package com.example.aiyl_bank.service.rep;

import com.example.aiyl_bank.dto.FootballerResponse;
import com.example.aiyl_bank.model.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FootballerRepo extends JpaRepository<Footballer, Long> {

    @Query("select new com.example.aiyl_bank.dto.FootballerResponse(" +
            "f.id,f.firstName,f.email,f.password,f.price)from Footballer f order by f.price asc ")
    List<FootballerResponse> getAllFootballersByAscPrice();

    @Query("select new com.example.aiyl_bank.dto.FootballerResponse(" +
            "f.id,f.firstName,f.email,f.password,f.price)from Footballer f order by f.price desc ")
    List<FootballerResponse> getAllFootballersByDescPrice();

    @Query("select new com.example.aiyl_bank.model.Footballer (" +
            "f.id,f.firstName,f.email,f.password,f.price)from Footballer f join Club cl on f.club.id=cl.id" +
            " where f.id=?1 and cl.id=?2")
    Optional<Footballer> getByIdAndClubId(Long id, Long clubId);

//    @Query(value = "SELECT id,firstName,email,price,password FROM students s WHERE s.email=?1 ",nativeQuery = true)
    @Query("SELECT new com.example.aiyl_bank.model.Footballer(" +
            "f.id,f.firstName,f.email,f.password,f.price)from Footballer f where f.email=?1")
    Optional<Footballer> getFootballerByEmail(String email);
}