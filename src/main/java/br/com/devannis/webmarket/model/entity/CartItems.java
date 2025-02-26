package br.com.devannis.webmarket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "TB_CART_ITEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CART_ITEMS")
    @SequenceGenerator(name = "SEQ_CART_ITEMS", sequenceName = "SEQ_CART_ITEMS", allocationSize = 1)
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;

    private BigDecimal priceAtTimeOfAddition;

    public CartItems(Cart cart, Product product, int quantity, BigDecimal priceAtTimeOfAddition) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.priceAtTimeOfAddition = priceAtTimeOfAddition;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
}
