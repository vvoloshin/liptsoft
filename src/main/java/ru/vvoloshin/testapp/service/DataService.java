package ru.vvoloshin.testapp.service;

import org.springframework.http.ResponseEntity;
import ru.vvoloshin.testapp.dto.response.Address;

import java.util.List;

public interface DataService {
    //    @Cacheable("address")
    ResponseEntity<Address> getAddress(List<String> payload);
}
