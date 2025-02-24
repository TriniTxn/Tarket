package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Address;

public record AddressResponseDTO(
        Long addressId,
        String street,
        String city,
        String state
) {
    public AddressResponseDTO(Address address) {
        this(address.getAddressId(),address.getStreet(), address.getCity(), address.getState());
    }
}
