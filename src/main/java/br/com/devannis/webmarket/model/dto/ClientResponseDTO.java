package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Client;

public record ClientResponseDTO(
        Long clientId,
        String clientName,
        String clientAddress,
        String idNumber
) {
    public ClientResponseDTO(Client client) {
        this(client.getClientId(), client.getClientName(), client.getClientAddress(), client.getIdNumber());
    }
}
