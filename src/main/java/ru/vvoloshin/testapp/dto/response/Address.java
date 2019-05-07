package ru.vvoloshin.testapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    private double geoLat;
    private double geoLon;
}
