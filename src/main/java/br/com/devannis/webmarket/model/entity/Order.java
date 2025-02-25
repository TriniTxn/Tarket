package br.com.devannis.webmarket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_ORDER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER")
    @SequenceGenerator(name = "SEQ_ORDER", sequenceName = "SEQ_ORDER", allocationSize = 1)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany
    private List<Cart> cart;

    private LocalDateTime orderDate;

    private String status;

    @OneToMany
    private List<OrderItems> orderItems;

    private double totalValue;

    public double getTotalValue() {
        if (orderItems == null) {
            return 0.0;
        }
        return orderItems
                .stream()
                .mapToDouble(item -> item.getProduct().getProductPrice() * item.getQuantity())
                .sum();
    }

    public double calculateTotalValue() {
        if (orderItems == null || orderItems.isEmpty()) {
            return 0.0;
        }
        return orderItems
                .stream()
                .mapToDouble(item -> item.getProduct().getProductPrice() * item.getQuantity())
                .sum();
    }
}
