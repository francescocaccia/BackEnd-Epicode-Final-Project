package main.repository;

import main.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
}
