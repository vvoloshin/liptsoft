package ru.vvoloshin.testapp.misc;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.vvoloshin.testapp.dto.response.Address;
import ru.vvoloshin.testapp.dto.response.DadataResponse;

@Service
public class AddressConverterImpl implements Converter<DadataResponse, Address> {
    @Override
    public Address convert(@NonNull DadataResponse from) {
        return new Address(from.getGeoLat(), from.getGeoLon());
    }
}
