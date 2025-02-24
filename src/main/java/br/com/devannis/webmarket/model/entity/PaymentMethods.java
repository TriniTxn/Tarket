package br.com.devannis.webmarket.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PAYMENT_METHODS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethods {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PAYMENT")
    @SequenceGenerator(name = "SEQ_PAYMENT", sequenceName = "SEQ_PAYMENT", allocationSize = 1)
    private Long paymentMethodId;

    private String paymentMethodName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
