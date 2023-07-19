package main.repository;

import main.entities.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecensioneRepository extends JpaRepository<Recensione, Long> {
    @Query("SELECT r FROM Recensione r WHERE r.ristorante.idRistorante = :idRistorante")
    List<Recensione> findAllByRistoranteId(Long idRistorante);



    @Query("SELECT r FROM Recensione r WHERE r.cliente.idCliente = :idCliente")
    List<Recensione> findAllByUtenteId(Long idCliente);



}
