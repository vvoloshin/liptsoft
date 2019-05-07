package ru.vvoloshin.testapp.service;

import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.vvoloshin.testapp.dto.response.DadataResponse;
import ru.vvoloshin.testapp.input.http.DataClient;
import ru.vvoloshin.testapp.misc.AddressConverterImpl;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataServiceTest {
    @Mock
    DataClient dataClient;
    @Spy
    AddressConverterImpl converter;
    @InjectMocks
    DataService dataService;

    @Test
    public void whenCorrectStringThenSuccess() {
        val payLoad = Collections.singletonList("[ \"спб жуковского 63\" ]");
        double expectedGeoLat = 59.9357374;
        double expectedGeoLon = 30.3632316;
        when(dataClient.sendMail(any(), any(), any()))
                .thenReturn(new ResponseEntity<>(Collections.singletonList(new DadataResponse(expectedGeoLat, expectedGeoLon)), HttpStatus.OK));
        val actualAddress = dataService.getAddress(payLoad).getBody();
        Assert.assertEquals(expectedGeoLat, actualAddress.getGeoLat(), 0);
        Assert.assertEquals(expectedGeoLon, actualAddress.getGeoLon(), 0);
    }

    @Test
    public void whenEmptyStringThenDoNothing() {
        val payLoad = Collections.singletonList("");
        dataService.getAddress(payLoad).getBody();
        Mockito.verify(converter, times(0)).convert(any());
    }
}