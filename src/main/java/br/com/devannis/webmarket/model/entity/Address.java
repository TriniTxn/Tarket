package br.com.devannis.webmarket.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_ADDRESS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
    @SequenceGenerator(name = "SEQ_ADDRESS", sequenceName = "SEQ_ADDRESS", allocationSize = 1)
    @Column(name = "address_id")
    private int addressId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "zip_code")
    private String zipCode;
    private String street;
    private String city;
    private String state;
}
