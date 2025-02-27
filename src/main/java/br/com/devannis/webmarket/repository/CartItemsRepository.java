package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.Cart;
import br.com.devannis.webmarket.model.entity.CartItems;
import br.com.devannis.webmarket.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    List<CartItems> findByCart(Cart cart);

    Optional<CartItems> findByCartAndProduct(Cart cart, Product product);

    void deleteByCart(Cart cart);

    void deleteByCartAndProduct(Cart cart, Product product);

    @Query("SELECT SUM(i.product.productPrice * i.quantity) FROM CartItems i WHERE i.cart.cartId = :cartId")
    Optional<BigDecimal> calculateTotalOrder(@Param("cartId") Long cartId);

    List<CartItems> findByCartCartId(Long cartId);
}
