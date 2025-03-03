package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.CartNotFoundException;
import br.com.devannis.webmarket.exception.InsufficientStockException;
import br.com.devannis.webmarket.exception.ProductNotFoundException;
import br.com.devannis.webmarket.mapper.CartItemMapper;
import br.com.devannis.webmarket.mapper.CartMapper;
import br.com.devannis.webmarket.model.dto.CartItemResponseDTO;
import br.com.devannis.webmarket.model.dto.CartResponseDTO;
import br.com.devannis.webmarket.model.entity.Cart;
import br.com.devannis.webmarket.model.entity.CartItems;
import br.com.devannis.webmarket.model.entity.Product;
import br.com.devannis.webmarket.model.enums.CartStatus;
import br.com.devannis.webmarket.repository.CartItemsRepository;
import br.com.devannis.webmarket.repository.CartRepository;
import br.com.devannis.webmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemsService {

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public CartItemsService(CartItemsRepository cartItemsRepository, ProductRepository productRepository) {
        this.cartItemsRepository = cartItemsRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public CartItemResponseDTO addCartItem(Long cartId, long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart not found"));

        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));

        // Valida o estoque do produto
        if (product.getStockQuantity() < quantity) {
            throw new InsufficientStockException(
                    "Insufficient stock for product: " + product.getProductName() +
                            ", Quantity available: " + product.getStockQuantity() +
                            ", Solicited Quantity: " + quantity
            );
        }

        Optional<CartItems> existingCartItems = cart
                .getCartItems()
                .stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst();

        CartItems cartItems;
        if(existingCartItems.isPresent()) {
            cartItems = existingCartItems.get();
            cartItems.setQuantity(cartItems.getQuantity() + quantity);
        } else {
            cartItems = new CartItems();
            cartItems.setCart(cart);
            cartItems.setProduct(product);
            cartItems.setQuantity(quantity);
            cart.getCartItems().add(cartItems);
        }

        CartItems updatedCartItem = cartItemsRepository.save(cartItems);

        return CartItemMapper.toDto(updatedCartItem);
    }


    public List<CartItems> getCartItemsByCartId(Long cartId) {
        return cartItemsRepository.findByCartCartId(cartId);
    }

    @Transactional
    public void removeCartItem(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart not found"));

        Optional<CartItems> existingItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst();

        if(existingItem.isPresent()) {
            CartItems cartItems = existingItem.get();

            int newQuantity = cartItems.getQuantity() - 1;
            if(newQuantity <= 0) {
                cart.getCartItems().remove(cartItems);
            } else {
                cartItems.setQuantity(newQuantity);
            }
        } else {
            throw new CartNotFoundException("Cart not found");
        }

        cartRepository.save(cart);
    }

    public CartResponseDTO confirmCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart not found"));

        cart.setCartStatus(CartStatus.valueOf("CONFIRMED"));

        Cart updatedCart = cartRepository.save(cart);

        return CartMapper.toDto(updatedCart);
    }

    @Transactional
    public CartItemResponseDTO updateItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        // Encontra o item no carrinho
        Optional<CartItems> existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItems item = existingItem.get();
            // Atualiza a quantidade do item
            item.setQuantity(quantity);
        } else {
            throw new RuntimeException("Item não encontrado no carrinho");
        }

        // Atualiza o carrinho no banco de dados
        Cart updatedCart = cartRepository.save(cart);

        // Converte para DTO e retorna
        return CartItemMapper.toDto(existingItem.get());
    }
}
