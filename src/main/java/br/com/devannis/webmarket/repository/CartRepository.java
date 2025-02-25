package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
