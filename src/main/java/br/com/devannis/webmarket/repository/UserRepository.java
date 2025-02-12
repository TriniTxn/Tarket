package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
