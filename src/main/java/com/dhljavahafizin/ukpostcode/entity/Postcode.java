package com.dhljavahafizin.ukpostcode.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "uk_postcodes")
public class Postcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    public Integer getId() {
        return id;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
