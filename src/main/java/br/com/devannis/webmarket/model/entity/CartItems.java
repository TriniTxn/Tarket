package br.com.devannis.webmarket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany
    private List<Cart> cart;

    @OneToMany
    private List<Product> products;

    private int quantity;
}
