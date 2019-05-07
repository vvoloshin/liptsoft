package ru.vvoloshin.testapp.misc;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vvoloshin.testapp.dto.response.Address;
import ru.vvoloshin.testapp.dto.response.DadataResponse;

@Slf4j
@Service
public class AddressConverterImpl implements Converter<DadataResponse, Address> {
    @Override
    public Address convert(@NonNull DadataResponse from) {
        Address address = null;
        try {
            address = new Address(from.getGeoLat(), from.getGeoLon());
        } catch (Exception e) {
            log.error("error from converting string: {}", from);
        }
        return address;
    }
}
