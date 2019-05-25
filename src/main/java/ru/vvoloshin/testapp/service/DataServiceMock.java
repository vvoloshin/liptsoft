package ru.vvoloshin.testapp.service;

import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vvoloshin.testapp.dto.response.Address;

import java.util.List;

@Service
@Profile("mock")
public class DataServiceMock implements DataService {
    @Override
    public ResponseEntity<Address> getAddress(List<String> payload) {
        val address = new Address(77.0123, 77.0123);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
}
