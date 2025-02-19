package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.ClientNotFoundException;
import br.com.devannis.webmarket.exception.UserAlreadyExists;
import br.com.devannis.webmarket.exception.UserNotFoundException;
import br.com.devannis.webmarket.model.dto.ClientExhibitionDTO;
import br.com.devannis.webmarket.model.dto.ClientRegisterDTO;
import br.com.devannis.webmarket.model.entity.Client;
import br.com.devannis.webmarket.model.entity.User;
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
        User user = userRepository.findById(clientDTO.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (clientRepository.existsByUser(user)) {
            throw new UserAlreadyExists("User already have a client registered");
        }

        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        client.setUser(user);

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

    public ClientExhibitionDTO updateClient(Client client) {
        Client existingClient = clientRepository.findById(client.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        client.setUser(existingClient.getUser());

        Client updatedClient = clientRepository.save(client);
        return new ClientExhibitionDTO(updatedClient);
    }

    public void deleteClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isPresent()) {
            System.out.println(clientOptional.get());

            clientRepository.delete(clientOptional.get());
        } else {
            throw new ClientNotFoundException("Client do not exist");
        }
    }
}
