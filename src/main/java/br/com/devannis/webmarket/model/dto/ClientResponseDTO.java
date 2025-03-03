package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record ClientResponseDTO(
        Long clientId,
        Long userId,
        String clientName,
        String idNumber,
        List<AddressResponseDTO> addresses,
        Long cartId
) {
    public ClientResponseDTO(Client client) {
        this(client.getClientId()
                ,client.getUser() != null ? client.getUser().getUserId() : null
                ,client.getClientName()
                ,client.getIdNumber()
                ,client.getAddresses() != null ?
                        client.getAddresses().stream().map(AddressResponseDTO::new)
                                .toList() : new ArrayList<>()
                ,client.getCart().getCartId());
    }
}
