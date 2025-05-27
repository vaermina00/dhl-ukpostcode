package com.dhljavahafizin.ukpostcode.service;

import com.dhljavahafizin.ukpostcode.dto.DistanceRequest;
import com.dhljavahafizin.ukpostcode.dto.DistanceResponse;
import com.dhljavahafizin.ukpostcode.dto.PostcodeLocation;
import com.dhljavahafizin.ukpostcode.entity.Postcode;
import com.dhljavahafizin.ukpostcode.exception.PostcodeNotFoundException;
import com.dhljavahafizin.ukpostcode.mapper.PostcodeMapper;
import com.dhljavahafizin.ukpostcode.repository.PostcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {

    private final PostcodeRepository postcodeRepository;
    private final PostcodeMapper postcodeMapper;

    @Autowired
    public DistanceService(PostcodeRepository postcodeRepository, PostcodeMapper postcodeMapper) {
        this.postcodeRepository = postcodeRepository;
        this.postcodeMapper = postcodeMapper;
    }

    private Postcode findPostcodeOrThrow(String postcode) {
        return postcodeRepository.findByPostcode(postcode.toUpperCase())
                .orElseThrow(() -> new PostcodeNotFoundException("Postcode not found: " + postcode));
    }

    private double calculateDistanceInKm(double lat1, double lon1, double lat2, double lon2) {
        final double NAUTICAL_TO_MILES = 1.1515;
        final double MILES_TO_KM = 1.609344;

        double longitudeDifference = lon1 - lon2;

        double angle = Math.acos(
                Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) +
                        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                                Math.cos(Math.toRadians(longitudeDifference))
        );

        double degrees = Math.toDegrees(angle);
        double nauticalMiles = degrees * 60;
        double statuteMiles = nauticalMiles * NAUTICAL_TO_MILES;

        return statuteMiles * MILES_TO_KM;
    }

    public DistanceResponse calculateDistance (DistanceRequest request) {

        Postcode location1 = findPostcodeOrThrow(request.getPostcode1());
        Postcode location2 = findPostcodeOrThrow(request.getPostcode2());

        PostcodeLocation dto1 = postcodeMapper.toLocation(location1);
        PostcodeLocation dto2 = postcodeMapper.toLocation(location2);

        double distance = calculateDistanceInKm(
                dto1.getLatitude(), dto1.getLongitude(),
                dto2.getLatitude(), dto2.getLongitude()
        );

        return DistanceResponse.builder()
                .location1(dto1)
                .location2(dto2)
                .distance(distance)
                .unit("km")
                .build();
    }
}
