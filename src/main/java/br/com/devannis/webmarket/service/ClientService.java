package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.ClientNotFoundException;
import br.com.devannis.webmarket.exception.UserAlreadyExistsException;
import br.com.devannis.webmarket.exception.UserNotFoundException;
import br.com.devannis.webmarket.model.dto.ClientResponseDTO;
import br.com.devannis.webmarket.model.dto.ClientRequestDTO;
import br.com.devannis.webmarket.model.entity.Address;
import br.com.devannis.webmarket.model.entity.Cart;
import br.com.devannis.webmarket.model.entity.Client;
import br.com.devannis.webmarket.model.entity.User;
import br.com.devannis.webmarket.repository.AddressRepository;
import br.com.devannis.webmarket.repository.ClientRepository;
import br.com.devannis.webmarket.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public ClientResponseDTO saveClient(ClientRequestDTO clientDTO) {
        User user = userRepository.findById(clientDTO.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (clientRepository.existsByUser(user)) {
            throw new UserAlreadyExistsException("User already have a client registered");
        }

        Client client = new Client();
        client.setClientName(clientDTO.clientName());
        client.setIdNumber(String.valueOf(clientDTO.idNumber()));
        client.setUser(user);
        client.setAddresses(new ArrayList<>());

        if(clientDTO.addresses() != null && !clientDTO.addresses().isEmpty()) {
            List<Address> addresses = clientDTO.addresses().stream()
                    .map(dto -> {
                        Address address = new Address();
                        address.setZipCode(dto.zipCode());
                        address.setStreet(dto.street());
                        address.setCity(dto.city());
                        address.setState(dto.state());
                        address.setClient(client);
                        return address;
                    }).toList();

            client.getAddresses().addAll(addresses);
        }

        Cart cart = new Cart();
        cart.setClient(client);
        client.setCart(cart);

        Client savedClient = clientRepository.save(client);

        return new ClientResponseDTO(savedClient);
    }

    @Transactional(readOnly = true)
    public ClientResponseDTO getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isPresent()) {
            return new ClientResponseDTO(clientOptional.get());
        } else {
            throw new ClientNotFoundException("Client do not exist");
        }
    }

    @Transactional
    public List<ClientResponseDTO> listAllClients() {
        return clientRepository
                .findAll()
                .stream()
                .map(ClientResponseDTO::new)
                .toList();
    }

    public ClientResponseDTO updateClient(Client client) {
        Client existingClient = clientRepository.findById(client.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        client.setUser(existingClient.getUser());

        Client updatedClient = clientRepository.save(client);
        return new ClientResponseDTO(updatedClient);
    }

    @Transactional
    public void deleteClientById(Long id) {
        Client existingClient = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));

        clientRepository.delete(existingClient);
    }
}
