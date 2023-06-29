package main.repository;

import main.entities.Cliente;
import main.entities.Ristorante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByEmailAndPassword(String email, String password);
}
