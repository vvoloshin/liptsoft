package ru.vvoloshin.testapp.input.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.vvoloshin.testapp.dto.response.DadataResponse;

import java.util.List;

@FeignClient(value = "dataClient", url = "${dadata.url.server}")
public interface DataClient {

    @PostMapping(value = "${dadata.url.post.address}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<List<DadataResponse>> sendMail(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String apiKey,
                                                  @RequestHeader(value = "X-Secret") String secretKey,
                                                  @RequestBody List<String> payLoad);
}
