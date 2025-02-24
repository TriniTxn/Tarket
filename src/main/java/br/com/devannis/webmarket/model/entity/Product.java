package br.com.devannis.webmarket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PRODUCTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT")
    @SequenceGenerator(name = "SEQ_PRODUCT", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "price")
    private Double productPrice;

    @Column(name = "stock")
    private Integer stockQuantity;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "added_at")
    private LocalDateTime addedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.addedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
