package br.com.devannis.webmarket.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TB_CLIENTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTS")
    @SequenceGenerator(name = "SEQ_CLIENTS", sequenceName = "SEQ_CLIENTS", allocationSize = 1)
    @Column(name = "client_id")
    private Long clientId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "id_number", nullable = false)
    private String idNumber;

    public void addAddress(Address address) {
        address.setClient(this);
        this.addresses.add(address);
    }

    public void removeAddress(Address address) {
        address.setClient(null);
        this.addresses.remove(address);
    }
}
