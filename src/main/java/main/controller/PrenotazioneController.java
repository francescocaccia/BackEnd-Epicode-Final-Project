package main.controller;

import main.payload.PrenotazionePayload;
import main.repository.RistoranteRepository;
import main.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/prenotazione")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private RistoranteRepository ristoranteRepository;

    @PostMapping("/prenota")
    public ResponseEntity<String> prenotaRistorante(@RequestBody PrenotazionePayload prenotazionePayload) {
        try {
            prenotazioneService.prenotaRistorante(prenotazionePayload);
            return new ResponseEntity<>("Prenotazione effettuata con successo.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modifica/{prenotazioneId}")
    public ResponseEntity<String> modificaPrenotazione(@RequestBody PrenotazionePayload prenotazionePayload,@PathVariable Long prenotazioneId) {
        try {
            prenotazioneService.modificaPrenotazione(prenotazionePayload, prenotazioneId);
            return new ResponseEntity<>("Prenotazione modificata con successo.", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/elimina/{clienteId}/{prenotazioneId}")
    public ResponseEntity<String> eliminaPrenotazione(@PathVariable("clienteId") Long clienteId, @PathVariable("prenotazioneId") Long prenotazioneId) {
        try {
            prenotazioneService.eliminaPrenotazione(clienteId, prenotazioneId);
            return new ResponseEntity<>("Prenotazione eliminata con successo.", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
