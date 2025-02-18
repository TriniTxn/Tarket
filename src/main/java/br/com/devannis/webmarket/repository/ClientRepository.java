package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
