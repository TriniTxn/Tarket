package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
}
