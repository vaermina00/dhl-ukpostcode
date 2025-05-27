package com.dhljavahafizin.ukpostcode.controller;

import com.dhljavahafizin.ukpostcode.dto.DistanceRequest;
import com.dhljavahafizin.ukpostcode.dto.DistanceResponse;
import com.dhljavahafizin.ukpostcode.service.DistanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class PostcodeController {

    @Autowired
    private DistanceService distanceService;

    @PostMapping("/v1/distance")
    public ResponseEntity<DistanceResponse> getDistance(@Valid @RequestBody DistanceRequest request) {
        DistanceResponse response = distanceService.calculateDistance(request);
        return ResponseEntity.ok(response);
    }
}
