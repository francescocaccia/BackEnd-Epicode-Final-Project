package main.repository;

import main.entities.Luogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LuogoRepository extends JpaRepository<Luogo, Long> {

    Luogo findByRegioneAndCittaAndIndirizzoAndNumeroCivico(String regione, String citta, String indirizzo, String numeroCivico);


}
