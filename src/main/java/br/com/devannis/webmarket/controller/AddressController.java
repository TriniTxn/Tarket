package br.com.devannis.webmarket.controller;

import br.com.devannis.webmarket.model.dto.AddressExhibitionDTO;
import br.com.devannis.webmarket.model.dto.AddressRegisterDTO;
import br.com.devannis.webmarket.model.entity.Address;
import br.com.devannis.webmarket.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/{clientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressExhibitionDTO createAddress(@PathVariable Long clientId, @RequestBody @Valid AddressRegisterDTO address) {
        return addressService.addAddress(clientId, address);
    }

    @RequestMapping(method = RequestMethod.GET, params = "client")
    @ResponseStatus(HttpStatus.OK)
    public List<AddressExhibitionDTO> getAddressesByClientId(@RequestParam @Valid Long client) {
        return addressService.getAddressesByClient(client);
    }

    @GetMapping("/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public AddressExhibitionDTO getAddressById(@PathVariable @Valid Long addressId) {
        return addressService.getAddressById(addressId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AddressExhibitionDTO> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public AddressExhibitionDTO updateAddress(Address address) {
        return addressService.updateAddress(address);
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddressById(@RequestParam Long addressId) {
        addressService.deleteAddress(addressId);
    }
}
