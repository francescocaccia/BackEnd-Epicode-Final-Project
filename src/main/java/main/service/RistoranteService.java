package main.service;

import lombok.Data;
import main.entities.Luogo;
import main.entities.Menu;
import main.entities.Piatto;
import main.entities.Ristorante;
import main.payload.LuogoPayload;
import main.payload.PiattoPayload;
import main.payload.RistorantePayload;
import main.repository.LuogoRepository;
import main.repository.RistoranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

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


        Luogo nuovoLuogo = new Luogo();
        nuovoLuogo.setRegione(luogoPayload.getRegione());
        nuovoLuogo.setCitta(luogoPayload.getCitta());
        nuovoLuogo.setIndirizzo(luogoPayload.getIndirizzo());
        ristorante.setLuogo(nuovoLuogo);


        ristoranteRepository.save(ristorante);
    }


    public void eliminaRistorante(Long id) throws ChangeSetPersister.NotFoundException {
        Ristorante found = ristoranteRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException("Ristorante non trovato "));

        ristoranteRepository.delete(found);
    }

    public List<Ristorante> findByName(String nomeRistorante) {
        return findByName(nomeRistorante);
    }


}


