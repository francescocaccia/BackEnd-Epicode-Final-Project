package main.repository;

import main.entities.CardImmagini;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardImmaginiRepository extends JpaRepository<CardImmagini, Long> {
}
