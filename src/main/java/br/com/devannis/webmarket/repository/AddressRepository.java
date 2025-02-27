package br.com.devannis.webmarket.repository;

import br.com.devannis.webmarket.model.entity.Address;
import br.com.devannis.webmarket.model.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByClient(Client client);

    Page<Address> findByClient(Client client, Pageable pageable);

    @Query("SELECT a FROM Address a WHERE a.client = :client AND a.city = :city")
    List<Address> findByClientAndCity(@Param("client") Client client, @Param("city") String city);
}
