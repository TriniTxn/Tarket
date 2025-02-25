package br.com.devannis.webmarket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public OrderItems(Order order, Product product, int quantity) {
    }
}
