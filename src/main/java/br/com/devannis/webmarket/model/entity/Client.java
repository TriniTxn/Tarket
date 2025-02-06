package br.com.devannis.webmarket.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "TB_CLIENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Client {

    @Id
    private UUID clientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_address")
    private String clientAddress;

    @Column(name = "id_number")
    private String idNumber;

    @OneToOne
    @JoinColumn(name = "user_user_id")
    private User user;
}
