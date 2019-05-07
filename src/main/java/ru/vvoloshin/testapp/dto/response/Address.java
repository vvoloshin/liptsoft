package ru.vvoloshin.testapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
public class Address {
    @NonNull
    private double geoLat;
    @NonNull
    private double geoLon;
}
