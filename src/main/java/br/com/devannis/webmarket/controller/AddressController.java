package br.com.devannis.webmarket.controller;

import br.com.devannis.webmarket.model.entity.Address;
import br.com.devannis.webmarket.service.AddressService;
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
    public ResponseEntity<Address> addAddress(@PathVariable Long clientId, @RequestBody Address address) {
        Address savedAddress = addressService.addAddress(clientId, address);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<Address>> getAddresses(@PathVariable Long clientId) {
        List<Address> addresses = addressService.getAddressesByClient(clientId);
        return ResponseEntity.ok(addresses);
    }
}
