package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.AddressNotFoundException;
import br.com.devannis.webmarket.exception.ClientNotFoundException;
import br.com.devannis.webmarket.model.dto.AddressResponseDTO;
import br.com.devannis.webmarket.model.dto.AddressRequestDTO;
import br.com.devannis.webmarket.model.entity.Address;
import br.com.devannis.webmarket.model.entity.Client;
import br.com.devannis.webmarket.repository.AddressRepository;
import br.com.devannis.webmarket.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public AddressResponseDTO addAddress(Long clientId, AddressRequestDTO addressDTO) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        Address address = new Address();

        address.setZipCode(addressDTO.zipCode());
        address.setStreet(addressDTO.street());
        address.setCity(addressDTO.city());
        address.setState(addressDTO.state());
        address.setClient(client);

        addressRepository.save(address);
        clientRepository.save(client);

        return new AddressResponseDTO(address);
    }

    public List<AddressResponseDTO> getAddressesByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        List<Address> addresses = addressRepository.findByClient(client);

        return addresses.stream().map(AddressResponseDTO::new).collect(Collectors.toList());
    }

    public AddressResponseDTO getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address do not exist"));

        return new AddressResponseDTO(address);
    }

    public List<AddressResponseDTO> getAllAddresses() {
        return addressRepository.findAll().stream().map(AddressResponseDTO::new).toList();
    }

    public AddressResponseDTO updateAddress(Long addressId, AddressRequestDTO address) {
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address do not exist"));

        existingAddress.setZipCode(address.zipCode());
        existingAddress.setCity(address.city());
        existingAddress.setState(address.state());
        existingAddress.setStreet(address.street());

        Address updatedAddress = addressRepository.save(existingAddress);
        return new AddressResponseDTO(updatedAddress);
    }

    public void deleteAddress(Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            throw new AddressNotFoundException("Address do not exist");
        }
        addressRepository.deleteById(addressId);
    }
}
