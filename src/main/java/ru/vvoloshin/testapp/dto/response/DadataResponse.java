package ru.vvoloshin.testapp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
@JsonIgnoreProperties
public class DadataResponse {
    @JsonProperty("geo_lat")
    @NonNull
    private Double geoLat;
    @JsonProperty("geo_lon")
    @NonNull
    private Double geoLon;
}
