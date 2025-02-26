package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByClientClientId(Long clientId);
}
