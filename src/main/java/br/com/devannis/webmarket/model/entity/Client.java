package br.com.devannis.webmarket.model.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_address")
    private String clientAddress;

    @Column(name = "id_number")
    private String idNumber;


}
