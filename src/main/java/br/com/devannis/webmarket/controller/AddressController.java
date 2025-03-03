package br.com.devannis.webmarket.controller;

import br.com.devannis.webmarket.model.dto.AddressResponseDTO;
import br.com.devannis.webmarket.model.dto.AddressRequestDTO;
import br.com.devannis.webmarket.model.entity.Address;
import br.com.devannis.webmarket.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/{clientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponseDTO createAddress(@PathVariable Long clientId, @RequestBody @Valid AddressRequestDTO address) {
        return addressService.addAddress(clientId, address);
    }

    @RequestMapping(method = RequestMethod.GET, params = "client")
    @ResponseStatus(HttpStatus.OK)
    public List<AddressResponseDTO> getAddressesByClientId(@RequestParam @Valid Long client) {
        return addressService.getAddressesByClient(client);
    }

    @GetMapping("/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponseDTO getAddressById(@PathVariable @Valid Long addressId) {
        return addressService.getAddressById(addressId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AddressResponseDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public AddressResponseDTO updateAddress(@PathVariable Long addressId, @RequestBody @Valid AddressRequestDTO addressDTO) {
        return addressService.updateAddress(addressId, addressDTO);
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddressById(@RequestParam Long addressId) {
        addressService.deleteAddress(addressId);
    }
}
