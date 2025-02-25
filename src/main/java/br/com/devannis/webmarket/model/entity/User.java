package br.com.devannis.webmarket.model.entity;

import br.com.devannis.webmarket.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS")
    @SequenceGenerator(name = "SEQ_USERS", sequenceName = "SEQ_USERS", allocationSize = 1)
    @Column(name = "user_id")
    private Long userId;

    @Getter
    private String username;

    @Getter
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @JoinColumn(name = "client_id")
    private Client client;

}
