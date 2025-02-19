package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.ClientNotFoundException;
import br.com.devannis.webmarket.model.dto.AddressExhibitionDTO;
import br.com.devannis.webmarket.model.dto.AddressRegisterDTO;
import br.com.devannis.webmarket.model.entity.Address;
import br.com.devannis.webmarket.model.entity.Client;
import br.com.devannis.webmarket.repository.AddressRepository;
import br.com.devannis.webmarket.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    public AddressExhibitionDTO addAddress(Long clientId, AddressRegisterDTO addressDTO) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        address.setClient(client);

        addressRepository.save(address);

        return new AddressExhibitionDTO(address);
    }

    public List<Address> getAddressesByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        return addressRepository.findByClient(client);
    }
}
