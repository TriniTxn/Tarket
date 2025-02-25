package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClientClientId(Long clientId);
}
