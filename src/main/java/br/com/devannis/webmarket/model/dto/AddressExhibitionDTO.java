package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Address;

public record AddressExhibitionDTO(
        Long addressId,
        String street,
        String city,
        String state
) {
    public AddressExhibitionDTO(Address address) {
        this(address.getAddressId(),address.getStreet(), address.getCity(), address.getState());
    }
}
