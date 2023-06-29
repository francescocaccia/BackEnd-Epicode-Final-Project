package main.service;

import main.entities.Cliente;
import main.entities.Prenotazione;
import main.entities.Ristorante;
import main.repository.ClienteRepository;
import main.repository.PrenotazioneRepository;
import main.repository.RistoranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private RistoranteRepository ristoranteRepository;
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public void prenotaRistorante(Long clienteId, Long ristoranteId, int numeroPersone, Date data) {
        // Verifica se il cliente esiste
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("Cliente non trovato.");
        }
        Cliente cliente = clienteOptional.get();

        // Verifica se il ristorante esiste
        Optional<Ristorante> ristoranteOptional = ristoranteRepository.findById(ristoranteId);
        if (ristoranteOptional.isEmpty()) {
            throw new RuntimeException("Ristorante non trovato.");
        }
        Ristorante ristorante = ristoranteOptional.get();

        // Effettua la prenotazione
        Prenotazione prenotazione = new Prenotazione(cliente, ristorante, new Date(), numeroPersone);
        prenotazioneRepository.save(prenotazione);

        // Aggiorna le associazioni tra cliente e prenotazione
        cliente.getPrenotazioni().add(prenotazione);
        clienteRepository.save(cliente);
    }


    public void eliminaPrenotazione(Long clienteId, Long prenotazioneId) {
        // Verifica se il cliente esiste
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("Cliente non trovato.");
        }
        Cliente cliente = clienteOptional.get();

        // Verifica se la prenotazione esiste
        Optional<Prenotazione> prenotazioneOptional = prenotazioneRepository.findById(prenotazioneId);
        if (prenotazioneOptional.isEmpty()) {
            throw new RuntimeException("Prenotazione non trovata.");
        }
        Prenotazione prenotazione = prenotazioneOptional.get();

        // Verifica se la prenotazione appartiene al cliente
        if (!cliente.getPrenotazioni().contains(prenotazione)) {
            throw new RuntimeException("La prenotazione non appartiene al cliente.");
        }

        // Rimuovi la prenotazione
        prenotazioneRepository.delete(prenotazione);
    }

    public void modificaPrenotazione(Long clienteId, Long prenotazioneId, int nuovoNumeroPersone, Date nuovaData) {
        // Verifica se il cliente esiste
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
        if (clienteOptional.isEmpty()) {
            throw new RuntimeException("Cliente non trovato.");
        }
        Cliente cliente = clienteOptional.get();

        // Verifica se la prenotazione esiste
        Optional<Prenotazione> prenotazioneOptional = prenotazioneRepository.findById(prenotazioneId);
        if (prenotazioneOptional.isEmpty()) {
            throw new RuntimeException("Prenotazione non trovata.");
        }
        Prenotazione prenotazione = prenotazioneOptional.get();

        // Verifica se la prenotazione appartiene al cliente
        if (!cliente.getPrenotazioni().contains(prenotazione)) {
            throw new RuntimeException("La prenotazione non appartiene al cliente.");
        }

        // Aggiorna il numero di persone della prenotazione
        prenotazione.setNumeroPersone(nuovoNumeroPersone);

        // Imposta la nuova data
        prenotazione.setDataPrenotazione(nuovaData);

        prenotazioneRepository.save(prenotazione);
    }


}
