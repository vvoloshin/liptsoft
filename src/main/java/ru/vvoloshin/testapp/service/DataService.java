package ru.vvoloshin.testapp.service;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vvoloshin.testapp.dto.response.Address;
import ru.vvoloshin.testapp.input.http.DataClient;
import ru.vvoloshin.testapp.misc.AddressConverterImpl;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Service
public class DataService {
    @NotNull
    @Value("${dadata.api.key}")
    private String apiKey;
    @NotNull
    @Value("${dadata.secret.key}")
    private String secretKey;
    private final DataClient dataClient;
    private final AddressConverterImpl converter;

    public DataService(DataClient dataClient, AddressConverterImpl converter) {
        this.dataClient = dataClient;
        this.converter = converter;
    }

    public ResponseEntity<Address> getAddress(List<String> payload) {
        if (!payload.isEmpty()) {
            try {
                val response = dataClient.sendMail(apiKey, secretKey, payload);
                if (response == null || !response.getStatusCode().is2xxSuccessful()) {
                    log.error("internal service error: {}", payload);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (response.getBody() != null && !response.getBody().isEmpty()) {
                    val address = converter.convert(response.getBody().get(0));
                    return new ResponseEntity<>(address, HttpStatus.OK);
                }
            } catch (FeignException e) {
                log.error("service unavailable");
                return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
            }
        }
        log.debug("empty request");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
