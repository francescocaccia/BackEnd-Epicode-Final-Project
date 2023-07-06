package main.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import main.entities.*;
import main.enums.TipoCucina;
import main.payload.CardImmaginiPayload;
import main.payload.LuogoPayload;
import main.payload.PiattoPayload;
import main.payload.RistorantePayload;
import main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
@Slf4j
public class RistoranteService {

    @Autowired
    private RistoranteRepository ristoranteRepository;
    @Autowired
    private LuogoRepository luogoRepository;
    @Autowired
    private CardImmaginiRepository cardImmaginiRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PiattoRepository piattoRepository;

    public void inserisciRistorante(RistorantePayload ristorantePayload) {
        // Verifica se esiste già un ristorante con lo stesso luogo
        LuogoPayload luogoPayload = ristorantePayload.getLuogo();
        Luogo luogoEsistente = luogoRepository.findByRegioneAndCittaAndIndirizzoAndNumeroCivico(
                luogoPayload.getRegione(), luogoPayload.getCitta(), luogoPayload.getIndirizzo(), luogoPayload.getNumeroCivico()
        );
        if (luogoEsistente != null) {
            throw new RuntimeException("Ristorante già presente con lo stesso luogo.");
        }


        Ristorante ristorante = new Ristorante();
        ristorante.setNomeRistorante(ristorantePayload.getNomeRistorante());
        ristorante.setTotaleCoperti(ristorantePayload.getTotaleCoperti());
        ristorante.setTipoCucina(ristorantePayload.getTipoCucina());

        Menu nuovoMenu = new Menu();
        List<PiattoPayload> piattiPayloadList = ristorantePayload.getMenu().getPiatti();
        List<Piatto> piattoEntityList = new ArrayList<>();

        piattiPayloadList.forEach(piattoPayload -> {
            Piatto piatto = new Piatto();
            piatto.setNomePiatto(piattoPayload.getNomePiatto());
            piatto.setPrezzo(piattoPayload.getPrezzo());
            piattoRepository.save(piatto);
            piattoEntityList.add(piatto);
        });

        nuovoMenu.setPiatti(piattoEntityList);

        menuRepository.save(nuovoMenu);

        ristorante.setMenu(nuovoMenu);

        Luogo nuovoLuogo = new Luogo();
        nuovoLuogo.setRegione(luogoPayload.getRegione());
        nuovoLuogo.setCitta(luogoPayload.getCitta());
        nuovoLuogo.setIndirizzo(luogoPayload.getIndirizzo());
        ristorante.setLuogo(nuovoLuogo);

        luogoRepository.save(nuovoLuogo);

        CardImmaginiPayload cardImmaginiPayload = ristorantePayload.getCardImmagini();
        CardImmagini cardImmagini = new CardImmagini();
        cardImmagini.setImmagine1(cardImmaginiPayload.getImmagine1());
        cardImmagini.setImmagine2(cardImmaginiPayload.getImmagine2());
        cardImmagini.setImmagine3(cardImmaginiPayload.getImmagine3());

        cardImmaginiRepository.save(cardImmagini);

        ristorante.setCardImmagini(cardImmagini);

        ristoranteRepository.save(ristorante);
        log.info("ristorante inserito correttamente" + ristorante);
    }


    public void eliminaRistorante(Long id) {
        Ristorante found = ristoranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ristorante non trovato"));

        ristoranteRepository.delete(found);
    }

    public List<Ristorante> findAll() {
        return ristoranteRepository.findAll();
    }

    public Optional<Ristorante> getById(Long id) {
        return ristoranteRepository.findById(id);
    }

    public List<Ristorante> getByTipoCucina(TipoCucina tipoCucina) {
        return ristoranteRepository.findByTipoCucina(tipoCucina);
    }



//    public List<Ristorante> searchRestaurants(String citta, String restaurantName) {
//        if (citta != null && restaurantName != null) {
//            // Ricerca per entrambi i criteri: city e restaurantName
//            return ristoranteRepository.findByLuogoAndNomeRistorante(citta, restaurantName);
//        } else if (citta != null) {
//            // Ricerca per city
//            return ristoranteRepository.findByLuogo(Luogo citta);
//        } else if (restaurantName != null) {
//            // Ricerca per restaurantName
//            return (List<Ristorante>) ristoranteRepository.findByNomeRistorante(restaurantName);
//        } else {
//            // Nessun criterio di ricerca specificato, restituisce tutti i ristoranti
//            return ristoranteRepository.findAll();
//        }
//    }
}

//    public List<Ristorante> getByCityAndName(String city, String name) {
//        return ristoranteRepository.findByLuogoCittaAndNomeRistoranteContainingIgnoreCase(city, name);
//    }



