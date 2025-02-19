package br.com.devannis.webmarket.controller;

import br.com.devannis.webmarket.model.dto.ClientExhibitionDTO;
import br.com.devannis.webmarket.model.dto.ClientRegisterDTO;
import br.com.devannis.webmarket.model.entity.Client;
import br.com.devannis.webmarket.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientExhibitionDTO createClient(@RequestBody @Valid ClientRegisterDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ClientExhibitionDTO> listAllClients() {
        return clientService.listAllClients();
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ClientExhibitionDTO getClientById(@RequestParam Long id) {
        return clientService.getClientById(id);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ClientExhibitionDTO updateClient(@RequestBody @Valid Client client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientById(@RequestParam Long id) {
        clientService.deleteClientById(id);
    }
}
