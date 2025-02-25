package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
