package main.repository;

import main.entities.Luogo;
import main.entities.Ristorante;
import main.enums.TipoCucina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RistoranteRepository extends JpaRepository<Ristorante, Long> {


    List<Ristorante> findByLuogo_CittaStartingWithIgnoreCase(String citta);


    List<Ristorante> findByTipoCucina(TipoCucina tipoCucina);

    List <Ristorante> findByNomeRistoranteStartingWithIgnoreCase(String nomeRistornate);



}