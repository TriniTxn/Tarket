package br.com.devannis.webmarket.controller;

import br.com.devannis.webmarket.model.dto.CartRequestDTO;
import br.com.devannis.webmarket.model.dto.CartResponseDTO;
import br.com.devannis.webmarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/confirm")
    public CartResponseDTO confirmCart(@PathVariable Long cartId) {
        return cartService.confirmCart(cartId);
    }

    @GetMapping("/{cartId}")
    public CartResponseDTO getCartById(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    @DeleteMapping("/{cartId}/clear")
    public CartResponseDTO clearCart(@PathVariable Long cartId) {
        return cartService.clearCart(cartId);
    }
}
