package br.com.devannis.webmarket.service;

import br.com.devannis.webmarket.exception.InsufficientStockException;
import br.com.devannis.webmarket.model.entity.Cart;
import br.com.devannis.webmarket.model.entity.CartItems;
import br.com.devannis.webmarket.model.entity.Product;
import br.com.devannis.webmarket.repository.CartItemsRepository;
import br.com.devannis.webmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemsService {

    @Autowired
    private CartItemsRepository cartItemsRepository;
    @Autowired
    private ProductRepository productRepository;

    public CartItemsService(CartItemsRepository cartItemsRepository, ProductRepository productRepository) {
        this.cartItemsRepository = cartItemsRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public CartItems addCartItem(Cart cart, Product product, int quantity) {
        // Valida o estoque do produto
        if (product.getStockQuantity() < quantity) {
            throw new InsufficientStockException(
                    "Estoque insuficiente para o produto: " + product.getProductName() +
                            ". Quantidade disponível: " + product.getStockQuantity() +
                            ", quantidade solicitada: " + quantity
            );
        }

        // Cria o item do carrinho
        CartItems cartItem = new CartItems();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        return cartItemsRepository.save(cartItem);
    }


    public List<CartItems> getCartItemsByCartId(Long cartId) {
        return cartItemsRepository.findByCartCartId(cartId);
    }

    @Transactional
    public void removeCartItem(Long cartItemId) {
        cartItemsRepository.deleteById(cartItemId);
    }
}
