package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.User;
import br.com.devannis.webmarket.model.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT a FROM User a WHERE a.email = :email")
    Optional<User> findByEmail(@Param("email")String email);

    @Query("SELECT a FROM User a WHERE LOWER(a.username) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> findByNameWithIgnoreCase(@Param("name") String name);

    @Query("SELECT a FROM User a WHERE a.role = :role")
    List<User> findByRole(@Param("role") UserRole role);

    @Query("SELECT a FROM User a WHERE a.createdAt > :date")
    List<User> findUsersCreatedAfter(@Param("date") LocalDateTime date);

    @Query("SELECT COUNT(a) FROM User a WHERE a.role = :role")
    long countByRole(@Param("role") UserRole role);

    @Query("SELECT a FROM User a ORDER BY a.username ASC")
    Page<User> findAllUsersWithPageable(Pageable pageable);
}
