package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    List<OrderItems> findByOrderOrderId(Long orderId);
}
