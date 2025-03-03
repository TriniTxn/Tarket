package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.CartNotFoundException;
import br.com.devannis.webmarket.mapper.CartMapper;
import br.com.devannis.webmarket.model.dto.CartResponseDTO;
import br.com.devannis.webmarket.model.entity.Cart;
import br.com.devannis.webmarket.model.entity.CartItems;
import br.com.devannis.webmarket.model.entity.Product;
import br.com.devannis.webmarket.repository.CartRepository;
import br.com.devannis.webmarket.repository.ClientRepository;
import br.com.devannis.webmarket.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;


    public CartService(CartRepository cartRepository, ClientRepository clientRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    public CartResponseDTO confirmCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        // Atualiza o status do carrinho para CONFIRMED
        cart.setStatus("CONFIRMED");

        // Atualiza o carrinho no banco de dados
        Cart updatedCart = cartRepository.save(cart);

        // Converte para DTO e retorna
        return CartMapper.toDto(updatedCart);
    }

    @Transactional
    public CartResponseDTO removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        // Remove o item do carrinho
        cart.getCartItems().removeIf(item -> item.getProduct().getProductId().equals(productId));

        // Atualiza o carrinho no banco de dados
        Cart updatedCart = cartRepository.save(cart);

        // Converte para DTO e retorna
        return CartMapper.toDto(updatedCart);
    }

    // Obtém o carrinho pelo ID
    public CartResponseDTO getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        // Converte para DTO e retorna
        return CartMapper.toDto(cart);
    }

    // Limpa o carrinho (remove todos os itens)
    @Transactional
    public CartResponseDTO clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        // Remove todos os itens do carrinho
        cart.getCartItems().clear();

        // Atualiza o carrinho no banco de dados
        Cart updatedCart = cartRepository.save(cart);

        // Converte para DTO e retorna
        return CartMapper.toDto(updatedCart);
    }
}
