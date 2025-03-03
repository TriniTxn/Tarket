package br.com.devannis.webmarket.controller;

import br.com.devannis.webmarket.model.dto.CartItemRequestDTO;
import br.com.devannis.webmarket.model.dto.CartItemResponseDTO;
import br.com.devannis.webmarket.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts/{cartId}/items")
public class CartItemsController {

    @Autowired
    private CartItemsService cartItemsService;

    @PostMapping()
    public CartItemResponseDTO addItemToCart(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return cartItemsService.addCartItem(cartId, productId, quantity);
    }

    @DeleteMapping("/{productId}")
    public void removeItemFromCart(
            @PathVariable Long cartId,
            @PathVariable Long productId
    ) {
        cartItemsService.removeCartItem(cartId, productId);
    }

    @PutMapping("/{productId}")
    public CartItemResponseDTO updateItemQuantity(
            @PathVariable Long cartId,
            @PathVariable Long productId,
            @RequestParam int quantity) {
        return cartItemsService.updateItemQuantity(cartId, productId, quantity);
    }
}
