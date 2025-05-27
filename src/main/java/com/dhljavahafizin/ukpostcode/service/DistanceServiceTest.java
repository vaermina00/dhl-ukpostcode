package com.dhljavahafizin.ukpostcode.service;

import com.dhljavahafizin.ukpostcode.dto.DistanceRequest;
import com.dhljavahafizin.ukpostcode.dto.PostcodeLocation;
import com.dhljavahafizin.ukpostcode.entity.Postcode;
import com.dhljavahafizin.ukpostcode.exception.PostcodeNotFoundException;
import com.dhljavahafizin.ukpostcode.mapper.PostcodeMapper;
import com.dhljavahafizin.ukpostcode.repository.PostcodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DistanceServiceTest {
    private PostcodeRepository postcodeRepository;
    private PostcodeMapper postcodeMapper;
    private DistanceService distanceService;

    @BeforeEach
    public void setup() {
        postcodeRepository = mock(PostcodeRepository.class);
        postcodeMapper = mock(PostcodeMapper.class);
        distanceService = new DistanceService(postcodeRepository, postcodeMapper);
    }

    @Test
    void testCalculateDistance_validRequest() {
        Postcode pc1 = new Postcode();
        pc1.setPostcode("SW1A 1AA");
        pc1.setLatitude("51.5010");
        pc1.setLongitude("-0.1416");

        Postcode pc2 = new Postcode();
        pc2.setPostcode("EC1A 1BB");
        pc2.setLatitude("51.5200");
        pc2.setLongitude("-0.0970");

        PostcodeLocation loc1 = new PostcodeLocation();
        loc1.setPostcode(pc1.getPostcode());
        loc1.setLatitude(51.5010);
        loc1.setLongitude(-0.1416);

        PostcodeLocation loc2 = new PostcodeLocation();
        loc2.setPostcode(pc2.getPostcode());
        loc2.setLatitude(51.5200);
        loc2.setLongitude(-0.0970);

        when(postcodeRepository.findByPostcode("SW1A 1AA")).thenReturn(Optional.of(pc1));
        when(postcodeRepository.findByPostcode("EC1A 1BB")).thenReturn(Optional.of(pc2));

        when(postcodeMapper.toLocation(pc1)).thenReturn(loc1);
        when(postcodeMapper.toLocation(pc2)).thenReturn(loc2);

        DistanceRequest request = new DistanceRequest();
        request.setPostcode1("SW1A 1AA");
        request.setPostcode2("EC1A 1BB");

        var response = distanceService.calculateDistance(request);

        assertNotNull(response);
        assertEquals("km", response.getUnit());
        assertTrue(response.getDistance() > 0);
    }

    @Test
    void testCalculateDistance_postcodeNotFound() {
        when(postcodeRepository.findByPostcode("INVALID")).thenReturn(Optional.empty());

        DistanceRequest request = new DistanceRequest();
        request.setPostcode1("INVALID");
        request.setPostcode2("EC1A 1BB");

        assertThrows(PostcodeNotFoundException.class, () -> {
            distanceService.calculateDistance(request);
        });
    }
}
