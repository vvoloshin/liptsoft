package ru.vvoloshin.testapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.vvoloshin.testapp.dto.response.Address;
import ru.vvoloshin.testapp.dto.response.DadataResponse;
import ru.vvoloshin.testapp.input.http.DataClient;
import ru.vvoloshin.testapp.misc.AddressConverterImpl;

import java.util.List;

@Slf4j
@Service
public class DataService {
    @Value("${dadata.api.key}")
    private String apiKey;
    @Value("${dadata.secret.key}")
    private String secretKey;
    private final DataClient dataClient;
    private final AddressConverterImpl converter;

    public DataService(DataClient dataClient, AddressConverterImpl converter) {
        this.dataClient = dataClient;
        this.converter = converter;
    }

    public ResponseEntity<Address> getAddress(String payload) {
        if (!StringUtils.isEmpty(payload)) {
            ResponseEntity<List<DadataResponse>> response = dataClient.sendMail(apiKey, secretKey, payload);
            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("error for receiving string: {}", payload);
                throw new RuntimeException("Service unavailable");
            }
            if (response.getBody() != null && !response.getBody().isEmpty()) {
                Address address = converter.convert(response.getBody().get(0));
                return new ResponseEntity<>(address, HttpStatus.OK);
            }
        }
        log.debug("empty request");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}