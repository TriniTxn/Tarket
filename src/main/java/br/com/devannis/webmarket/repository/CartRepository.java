package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.Cart;
import br.com.devannis.webmarket.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByClientClientId(Long clientId);

    List<Cart> findByStatus(String status);

    @Query("SELECT a FROM Cart a WHERE a.client.clientId = :clientId AND a.status = :status")
    Optional<Cart> findByClientAndStatus(@Param("clientId") Long clientId, @Param("status") String status);

    @Query("SELECT DISTINCT a FROM Cart a JOIN a.cartItems ai WHERE ai.product.productId = :productId")
    List<Cart> findCartsContainingProduct(@Param("productId") Long productId);

    @Query(
            "SELECT a FROM Cart a WHERE (SELECT SUM(ai.product.productPrice * ai.quantity) FROM CartItems ai WHERE ai.cart = a) > :totalValue"
    )
    List<Cart> findCartsWithTotalValueGreaterThan(@Param("totalValue") BigDecimal totalValue);

    @Query(
            "SELECT a FROM Cart a WHERE (SELECT SUM(ai.product.productPrice * ai.quantity) FROM CartItems ai WHERE ai.cart = a) < :totalValue"
    )
    List<Cart> findCartsWithTotalValueLessThan(@Param("totalValue") BigDecimal totalValue);

    @Query(
            "SELECT a FROM Cart a WHERE (SELECT SUM(ai.product.productPrice * ai.quantity) FROM CartItems ai WHERE ai.cart = a) BETWEEN :minValue AND :maxValue"
    )
    List<Cart> findCartsWithTotalValueBetween(@Param("minValue") BigDecimal minItems, @Param("maxValue") BigDecimal maxItems);

    @Query("SELECT a FROM Cart a WHERE (SELECT COUNT(ai) FROM CartItems ai WHERE ai.cart = a) >= :minItems")
    List<Cart> findCartsWithMinItems(@Param("minItems") int minItems);




}
