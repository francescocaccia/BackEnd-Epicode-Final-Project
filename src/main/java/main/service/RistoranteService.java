package main.service;

import lombok.Data;
import main.entities.*;
import main.enums.TipoCucina;
import main.payload.CardImmaginiPayload;
import main.payload.LuogoPayload;
import main.payload.PiattoPayload;
import main.payload.RistorantePayload;
import main.repository.LuogoRepository;
import main.repository.RistoranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class RistoranteService {

    @Autowired
    private RistoranteRepository ristoranteRepository;
    @Autowired
    private LuogoRepository luogoRepository;

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
        ristorante.setTotaleCoperti(ristorantePayload.getTotaleCoperti());
        ristorante.setTipoCucina(ristorantePayload.getTipoCucina());

        Menu nuovoMenu = new Menu();
        List<PiattoPayload> piattiPayloadList = ristorantePayload.getMenu().getPiatti();
        List<Piatto> piattoEntityList = new ArrayList<>();

        piattiPayloadList.forEach(piattoPayload -> {
            Piatto piatto = new Piatto();
            piatto.setNomePiatto(piattoPayload.getNomePiatto());
            piatto.setPrezzo(piattoPayload.getPrezzo());
            piattoEntityList.add(piatto);
        });

        nuovoMenu.setPiatti(piattoEntityList);

        ristorante.setMenu(nuovoMenu);

        Luogo nuovoLuogo = new Luogo();
        nuovoLuogo.setRegione(luogoPayload.getRegione());
        nuovoLuogo.setCitta(luogoPayload.getCitta());
        nuovoLuogo.setIndirizzo(luogoPayload.getIndirizzo());
        ristorante.setLuogo(nuovoLuogo);

        CardImmaginiPayload cardImmaginiPayload = ristorantePayload.getCardImmagini();
        CardImmagini cardImmagini = new CardImmagini();
        cardImmagini.setImmagine1(cardImmaginiPayload.getImmagine1());
        cardImmagini.setImmagine2(cardImmaginiPayload.getImmagine2());
        cardImmagini.setImmagine3(cardImmaginiPayload.getImmagine3());

        ristorante.setCardImmagini(cardImmagini);

        ristoranteRepository.save(ristorante);
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


}


