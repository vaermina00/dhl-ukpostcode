package com.dhljavahafizin.ukpostcode.repository;

import com.dhljavahafizin.ukpostcode.entity.Postcode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostcodeRepository extends JpaRepository<Postcode, Integer> {
    Optional<Postcode> findByPostcode(String postcode);
}
