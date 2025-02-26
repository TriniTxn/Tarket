package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Client;

import java.util.List;
import java.util.stream.Collectors;

public record ClientResponseDTO(
        Long clientId,
        Long userId,
        String clientName,
        String idNumber,
        List<AddressResponseDTO> addresses
) {
    public ClientResponseDTO(Client client) {
        this(client.getClientId()
                ,client.getUser().getUserId()
                ,client.getClientName()
                ,client.getIdNumber()
                ,client.getAddresses().stream().map(AddressResponseDTO::new).toList());
    }
}
