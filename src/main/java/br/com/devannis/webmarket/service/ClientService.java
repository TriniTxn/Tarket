package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.ClientNotFoundException;
import br.com.devannis.webmarket.model.dto.ClientExhibitionDTO;
import br.com.devannis.webmarket.model.dto.ClientRegisterDTO;
import br.com.devannis.webmarket.model.entity.Client;
import br.com.devannis.webmarket.repository.ClientRepository;
import br.com.devannis.webmarket.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserRepository userRepository;

    public ClientExhibitionDTO saveClient(ClientRegisterDTO clientDTO) {
        Client client = new Client();

        BeanUtils.copyProperties(clientDTO, client);
        Client savedClient = clientRepository.save(client);
        return new ClientExhibitionDTO(savedClient);
    }

    public ClientExhibitionDTO getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isPresent()) {
            return new ClientExhibitionDTO(clientOptional.get());
        } else {
            throw new ClientNotFoundException("Client do not exist");
        }
    }

    public List<ClientExhibitionDTO> listAllClients() {
        return clientRepository
                .findAll()
                .stream()
                .map(ClientExhibitionDTO::new)
                .toList();
    }
}
