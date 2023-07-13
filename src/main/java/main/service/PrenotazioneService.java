package main.service;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import main.entities.Cliente;
import main.entities.Prenotazione;
import main.entities.Ristorante;
import main.payload.PrenotazionePayload;
import main.repository.ClienteRepository;
import main.repository.PrenotazioneRepository;
import main.repository.RistoranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class PrenotazioneService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private RistoranteRepository ristoranteRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public void prenotaRistorante(PrenotazionePayload prenotazionePayload) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(prenotazionePayload.getIdCliente());

        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("Cliente non trovato.");
        }
        Cliente cliente = clienteOptional.get();

        Optional<Prenotazione> pi = cliente.getPrenotazioni().stream().filter(p -> p.getDataPrenotazione().getTime() == prenotazionePayload.getDataPrenotazione().getTime()).findAny();

        if (pi.isPresent()) {
            throw new RuntimeException("hai gia una prenotazione a questo orario");
        }


        // Verifica se il ristorante esiste
        Optional<Ristorante> ristoranteOptional = ristoranteRepository.findById(prenotazionePayload.getIdRistorante());
        if (ristoranteOptional.isEmpty()) {
            throw new RuntimeException("Ristorante non trovato.");
        }
        Ristorante ristorante = ristoranteOptional.get();
        // Verifica se il ristorante ha i coperti necessari
        int sum = ristorante.getPrenotazioni().stream().mapToInt(p -> p.getNumeroPersone()).sum();

        if (sum + prenotazionePayload.getNumeroPersone() > ristorante.getTotaleCoperti()) {
            throw new RuntimeException("numero posti non disponibili");
        }

        // Effettua la prenotazione
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataPrenotazione(prenotazionePayload.getDataPrenotazione());
        prenotazione.setNumeroPersone(prenotazionePayload.getNumeroPersone());
        prenotazione.setCliente(cliente);
        prenotazione.setRistorante(ristorante);
        prenotazioneRepository.save(prenotazione);

        // Aggiorna le associazioni tra cliente e prenotazione
        cliente.addPrenotazioni(List.of(prenotazione));
        clienteRepository.save(cliente);
        ristorante.addPrenotazioni(List.of(prenotazione));
        ristoranteRepository.save(ristorante);
    }


    public void eliminaPrenotazione(Long clienteId, Long prenotazioneId) {
        // Verifica se il cliente esiste
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
        log.info("cerco di eliminare la prenotazione tramite id " + prenotazioneId);
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("Cliente non trovato.");
        }
        Cliente cliente = clienteOptional.get();
        Optional<Prenotazione> prenotazione = cliente.getPrenotazioni().stream().filter(p -> Objects.equals(p.getIdPrenotazione(), prenotazioneId)).findAny();

        // Rimuovi la prenotazione
        if (prenotazione.isPresent()) {
            Prenotazione prenotazioneDelete = prenotazione.get();
            prenotazioneRepository.delete(prenotazioneDelete);
            log.info("prenotazione eliminata ");
        }
    }

    public void modificaPrenotazione(PrenotazionePayload prenotazionePayload, Long idPrenotazione) {
        // Verifica se il cliente esiste (per sicurezza)
        Optional<Cliente> clienteOptional = clienteRepository.findById(prenotazionePayload.getIdCliente());
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("Cliente non trovato.");
        }
        Cliente cliente = clienteOptional.get();

        Optional<Prenotazione> prenotazione = cliente.getPrenotazioni().stream().filter(p -> Objects.equals(p.getIdPrenotazione(), idPrenotazione)).findAny();
        if (prenotazione.isPresent()) {
            Prenotazione pi = prenotazione.get();
            int vecchiaPrenotazione = pi.getNumeroPersone();
            pi.setDataPrenotazione(prenotazionePayload.getDataPrenotazione());
            Optional<Ristorante> ristorante = ristoranteRepository.findById(prenotazionePayload.getIdRistorante());
            if (ristorante.isPresent()) {
                Ristorante ri = ristorante.get();
                int sum = ri.getPrenotazioni().stream().mapToInt(p -> p.getNumeroPersone()).sum();

                int differenzaTraPrenotazioni = sum - vecchiaPrenotazione;

                if (prenotazionePayload.getNumeroPersone() <= (ri.getTotaleCoperti() - differenzaTraPrenotazioni) ) {

                    pi.setNumeroPersone(prenotazionePayload.getNumeroPersone());
                    prenotazioneRepository.save(pi);

                } else {
                    throw new RuntimeException("il numero di posti selezionato non è disponibile");
                }

            }
        }
    }
}
