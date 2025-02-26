package br.com.devannis.webmarket.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_ADDRESS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
    @SequenceGenerator(name = "SEQ_ADDRESS", sequenceName = "SEQ_ADDRESS", allocationSize = 1)
    @Column(name = "address_id")
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    public void setClient(Client client) {
        this.client = client;
        if (client != null && !client.getAddresses().contains(this)) {
            client.getAddresses().add(this);
        }
    }
}
