package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.PaymentMethods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodsRepository extends JpaRepository<PaymentMethods, Long> {
}
