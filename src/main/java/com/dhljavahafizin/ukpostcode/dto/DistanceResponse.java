package com.dhljavahafizin.ukpostcode.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DistanceResponse {
    private PostcodeLocation location1;
    private PostcodeLocation location2;
    private Double distance;
    private String unit;
}
