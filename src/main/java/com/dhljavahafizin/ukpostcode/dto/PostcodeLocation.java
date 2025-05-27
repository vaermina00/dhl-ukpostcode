package com.dhljavahafizin.ukpostcode.dto;

import lombok.Data;

@Data
public class PostcodeLocation {
    private String postcode;
    private Double latitude;
    private Double longitude;

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
