package ru.vvoloshin.testapp.output.http;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vvoloshin.testapp.dto.response.Address;
import ru.vvoloshin.testapp.service.DataService;

import java.util.Collections;

@RestController
public class DataController {
    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("${outer.url.post.address}")
    public ResponseEntity<Address> getAddress(@RequestBody String payload) {
        return dataService.getAddress(Collections.singletonList(payload));
    }
}
