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

import java.util.*;

@Data
@Service
@Slf4j
public class RistoranteService {

    @Autowired
    private RistoranteRepository ristoranteRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private LuogoRepository luogoRepository;
    @Autowired
    private CardImmaginiRepository cardImmaginiRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PiattoRepository piattoRepository;
    @Autowired
    private ClienteService clienteService;

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
        Cliente cliente = clienteService.findByEmail(ristorantePayload.getEmailProprietario());
        ristorante.setCliente(cliente);
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

        cliente.addRestaurant(List.of(ristorante));
        clienteRepository.saveAndFlush(cliente);

        ristoranteRepository.save(ristorante);

        log.info("ristorante inserito correttamente" + ristorante);
    }


    public void eliminaRistorante(Long id) {
        log.info("tento di cancellare ristorante con id: " + id);
        Ristorante found = ristoranteRepository.findById(id)

                .orElseThrow(() -> new RuntimeException("Ristorante non trovato"));

        ristoranteRepository.delete(found);
        log.info("ristorante con id: " + id + " eliminato con successo");
    }

    public List<Ristorante> findAll() {
        return ristoranteRepository.findAll();
    }

    public Optional<Ristorante> getById(Long id) {
        return ristoranteRepository.findById(id);
    }



    public List<Ristorante> findByCitta(String citta) {
        return ristoranteRepository.findByLuogo_CittaStartingWithIgnoreCase(citta);
    }


    public List<Ristorante> searchByTipoCucinaOrNomeRistorante(String value) {
        TipoCucina[] allPossibleTipoCucina = TipoCucina.values();

        boolean anyMatch = Arrays.stream(allPossibleTipoCucina)
                .anyMatch(tipoCucina -> tipoCucina.name().equalsIgnoreCase(value));
        if (anyMatch) {
            return ristoranteRepository.findByTipoCucina(TipoCucina.valueOf(value));
        } else {
            Optional<Ristorante> ristoranteOptional = ristoranteRepository.findByNomeRistoranteStartingWithIgnoreCase(value);
            if (ristoranteOptional.isPresent()) {
                return List.of(ristoranteOptional.get());
            }
        }
        return new ArrayList<>();
    }


    public List<Ristorante> searchByTipoCucinaOrNameRestaurantAndCity(String cucinaRistorante, String citta) {
        return searchByTipoCucinaOrNomeRistorante(cucinaRistorante).stream().filter(element -> element.getLuogo().getCitta().equals(citta)).toList();
    }

}
