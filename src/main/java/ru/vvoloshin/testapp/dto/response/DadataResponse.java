package ru.vvoloshin.testapp.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties
public class DadataResponse {
    @JsonProperty("geo_lat")
    private Double geoLat;
    @JsonProperty("geo_lon")
    private Double geoLon;
}
