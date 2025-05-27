package com.dhljavahafizin.ukpostcode.mapper;

import com.dhljavahafizin.ukpostcode.dto.PostcodeLocation;
import com.dhljavahafizin.ukpostcode.entity.Postcode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PostcodeMapper {
    @Mapping(source = "latitude", target = "latitude", qualifiedByName = "toDouble")
    @Mapping(source = "longitude", target = "longitude", qualifiedByName = "toDouble")
    PostcodeLocation toLocation(Postcode postcode);

    @Named("toDouble")
    static Double toDouble(String value) {
        return value != null ? Double.parseDouble(value) : null;
    }
}