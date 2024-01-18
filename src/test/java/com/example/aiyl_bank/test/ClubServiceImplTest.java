package com.example.aiyl_bank.test;

import com.example.aiyl_bank.dto.ClubRequest;
import com.example.aiyl_bank.dto.ClubResponse;
import com.example.aiyl_bank.model.Club;
import com.example.aiyl_bank.model.Country;
import com.example.aiyl_bank.service.impl.ClubServiceImpl;
import com.example.aiyl_bank.service.rep.ClubRepo;
import com.example.aiyl_bank.service.rep.CountryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ClubServiceImplTest {

    @Mock
    private CountryRepo countryRepo;

    @Mock
    private ClubRepo clubRepo;

    @InjectMocks
    private ClubServiceImpl clubService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddClubToCountry() {
        Long countryId = 1L;
        ClubRequest clubRequest = new ClubRequest("ClubName", BigDecimal.valueOf(1000));
        Country country = new Country();
        country.setId(countryId);
        when(countryRepo.findById(eq(countryId))).thenReturn(Optional.of(country));
        when(clubRepo.save(any())).thenReturn(new Club());
        String result = clubService.addClubToCountry(countryId, clubRequest);
        assertNotNull(result);
        assertTrue(result.contains(countryId.toString()));
        assertTrue(result.contains("ысымдагы клуб кошулду"));
        verify(countryRepo, times(1)).findById(eq(countryId));
        verify(clubRepo, times(1)).save(any());
    }

    @Test
    void testGetById() {
        Long clubId = 1L;
        Club club = new Club();
        club.setId(clubId);
        when(clubRepo.findById(eq(clubId))).thenReturn(Optional.of(club));
        ClubResponse result = clubService.getById(clubId);
        assertNotNull(result);
        assertEquals(clubId, result.getId());
        verify(clubRepo, times(1)).findById(eq(clubId));
    }

    @Test
    void testGetClubsByCountryId() {
        Long countryId = 1L;
        when(clubRepo.getClubsByCountryId(eq(countryId))).thenReturn(Collections.emptyList());
        List<ClubResponse> result = clubService.getClubsByCountryId(countryId);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(clubRepo, times(1)).getClubsByCountryId(eq(countryId));
    }

    @Test
    void tesUpdateClubByIdAndCountryId() {
        Long clubId = 1L;
        Long countryId = 1L;

        Country country = new Country();
        country.setId(countryId);
        when(countryRepo.findById(eq(countryId))).thenReturn(Optional.of(country));
        Club existingClub = new Club();
        existingClub.setId(clubId);
        existingClub.setCountry(country);
        when(clubRepo.getByIdAndCountryId(eq(clubId), eq(countryId))).thenReturn(Optional.of(existingClub));
        when(clubRepo.save(any())).thenReturn(new Club());
        ClubRequest clubRequest = new ClubRequest();
        clubRequest.setName("UpdatedClubName");
        clubRequest.setPrice(BigDecimal.valueOf(1500));
        String result = clubService.updateClubByIdAndCountryId(clubId, countryId, clubRequest);
        assertNotNull(result);
        assertTrue(result.contains(countryId.toString()));
        assertTrue(result.contains("аттуу клубу ийгиликтуу озгорду"));

        verify(countryRepo, times(1)).findById(eq(countryId));
        verify(clubRepo, times(1)).getByIdAndCountryId(eq(clubId), eq(countryId));
        verify(clubRepo, times(1)).save(any());
    }

@Test
void deleteByIdAndCountryId() {
    Long clubId = 1L;
    Long countryId = 1L;
    Club existingClub = new Club();
    existingClub.setId(clubId);
    when(clubRepo.getByIdAndCountryId(eq(clubId), eq(countryId))).thenReturn(Optional.of(existingClub));
    String result = clubService.deleteByIdAndCountryId(clubId, countryId);
    assertNotNull(result);
    assertTrue(result.contains(clubId.toString()));
    assertTrue(result.contains("ийгиликтуу очурулду"));
    verify(clubRepo, times(1)).getByIdAndCountryId(eq(clubId), eq(countryId));
    verify(clubRepo, times(1)).delete(eq(existingClub));
}
}