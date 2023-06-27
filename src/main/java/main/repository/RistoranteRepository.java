package main.repository;

import main.entities.Ristorante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RistoranteRepository extends JpaRepository<Ristorante, Long> {
}
