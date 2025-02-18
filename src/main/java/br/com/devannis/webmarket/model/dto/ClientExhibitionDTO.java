package br.com.devannis.webmarket.model.dto;

import br.com.devannis.webmarket.model.entity.Client;
import br.com.devannis.webmarket.model.entity.User;

public record ClientExhibitionDTO(
        Long clientId,
        String clientName
) {
    public ClientExhibitionDTO(Client client) {
        this(client.getClientId(), client.getClientName());
    }
}
