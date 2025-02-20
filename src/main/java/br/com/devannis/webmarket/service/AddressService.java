package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.AddressNotFoundException;
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
import java.util.stream.Collectors;

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

    public List<AddressExhibitionDTO> getAddressesByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        List<Address> addresses = addressRepository.findByClient(client);

        return addresses.stream().map(AddressExhibitionDTO::new).collect(Collectors.toList());
    }

    public AddressExhibitionDTO getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Address do not exist"));

        return new AddressExhibitionDTO(address);
    }

    public List<AddressExhibitionDTO> getAllAddresses() {
        return addressRepository.findAll().stream().map(AddressExhibitionDTO::new).toList();
    }

    public AddressExhibitionDTO updateAddress(Address address) {
        Address existingAddress = addressRepository.findById(address.getAddressId())
                .orElseThrow(() -> new AddressNotFoundException("Address do not exist"));

        address.setClient(existingAddress.getClient());

        Address updatedAddress = addressRepository.save(address);
        return new AddressExhibitionDTO(updatedAddress);
    }

    public void deleteAddress(Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            throw new AddressNotFoundException("Address do not exist");
        }
        addressRepository.deleteById(addressId);
    }
}
