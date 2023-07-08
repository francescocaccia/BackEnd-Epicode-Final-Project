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

    List<Ristorante> findByNomeRistoranteStartingWithIgnoreCase(String nomeRistornate);

    List<Ristorante> findByTipoCucina(TipoCucina tipoCucina);

    Optional<Ristorante> findByNomeRistorante(String nomeRistornate);

    List<Ristorante> findByLuogoAndNomeRistorante(Luogo city, String restaurantName);

    List<Ristorante> findByLuogo_CittaStartingWithIgnoreCaseAndTipoCucinaAndNomeRistoranteStartingWithIgnoreCase(
            String citta, TipoCucina tipoCucina, String nomeRistorante
    );

    List<Ristorante> findByLuogo_CittaStartingWithIgnoreCaseAndNomeRistoranteStartingWithIgnoreCase(
            String citta, String nomeRistorante
    );

    List<Ristorante> findByLuogo_CittaStartingWithIgnoreCaseAndTipoCucina(String citta, TipoCucina tipoCucina);

}