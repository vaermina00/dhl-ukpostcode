package com.dhljavahafizin.ukpostcode.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class DistanceRequest {
    @NotBlank(message = "Postcode 1 cannot be blank")
    private String postcode1;
    @NotBlank(message = "Postcode 2 cannot be blank")
    private String postcode2;
}
