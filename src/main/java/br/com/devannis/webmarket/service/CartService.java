package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.CartNotFoundException;
import br.com.devannis.webmarket.model.dto.CartResponseDTO;
import br.com.devannis.webmarket.model.entity.Cart;
import br.com.devannis.webmarket.repository.CartRepository;
import br.com.devannis.webmarket.repository.ClientRepository;
import br.com.devannis.webmarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ClientRepository clientRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public CartResponseDTO getCartByClient(Long clientId) {
        Cart cart = cartRepository.findByClientClientId(clientId).orElseThrow(() -> new CartNotFoundException("Cart not found"));

        return CartMapper.toDto(cart);
    }
}
