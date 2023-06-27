package main.repository;

import main.entities.Luogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LuogoRepository extends JpaRepository<Luogo, Long> {
}
