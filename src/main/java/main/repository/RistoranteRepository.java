package main.repository;

import main.entities.Luogo;
import main.entities.Ristorante;
import main.enums.TipoCucina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RistoranteRepository extends JpaRepository<Ristorante, Long> {

    Ristorante findByLuogo(Luogo luogo);

    Ristorante findByName(String nomeRistornate);

    List<Ristorante> findByTipoCucina(TipoCucina tipoCucina);
}
