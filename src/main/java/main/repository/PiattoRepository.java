package main.repository;

import main.entities.Piatto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PiattoRepository extends JpaRepository<Piatto, Long> {
}
