package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Client;

public record ClientExhibitionDTO(
        Long clientId,
        String clientName,
        String clientAddress,
        String idNumber
) {
    public ClientExhibitionDTO(Client client) {
        this(client.getClientId(), client.getClientName(), client.getClientAddress(), client.getIdNumber());
    }
}
