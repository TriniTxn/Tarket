package br.com.devannis.webmarket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_ORDER_ITEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER_ITEM")
    @SequenceGenerator(name = "SEQ_ORDER_ITEM", sequenceName = "SEQ_ORDER_ITEM", allocationSize = 1)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;

    @Column(name = "price_at_time_of_order", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceAtTimeOfOrder;

    public OrderItems(Order order, Product product, int quantity, BigDecimal priceAtTimeOfOrder) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.priceAtTimeOfOrder = priceAtTimeOfOrder;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0 ) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
}
