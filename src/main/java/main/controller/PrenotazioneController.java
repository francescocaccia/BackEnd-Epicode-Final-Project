package main.controller;

import main.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;


    @PostMapping("/prenota/{clienteId}/{ristoranteId}")
    public ResponseEntity<String> prenotaRistorante(@PathVariable("clienteId") Long clienteId, @PathVariable("ristoranteId") Long ristoranteId, @RequestParam("numeroPersone") int numeroPersone, @RequestParam("data") @DateTimeFormat(pattern = "dd-MM-yyyy") Date data) {
        try {
            prenotazioneService.prenotaRistorante(clienteId, ristoranteId, numeroPersone, data);
            return new ResponseEntity<>("Prenotazione effettuata con successo.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/modifica/{clienteId}/{prenotazioneId}")
    public ResponseEntity<String> modificaPrenotazione(@PathVariable("clienteId") Long clienteId, @PathVariable("prenotazioneId") Long prenotazioneId, @RequestParam("numeroPersone") int nuovoNumeroPersone, @RequestParam("data") @DateTimeFormat(pattern = "dd-MM-yyyy") Date nuovaData) {
        try {
            prenotazioneService.modificaPrenotazione(clienteId, prenotazioneId, nuovoNumeroPersone, nuovaData);
            return new ResponseEntity<>("Prenotazione modificata con successo.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/elimina/{clienteId}/{prenotazioneId}")
    public ResponseEntity<String> eliminaPrenotazione(@PathVariable("clienteId") Long clienteId, @PathVariable("prenotazioneId") Long prenotazioneId) {
        try {
            prenotazioneService.eliminaPrenotazione(clienteId, prenotazioneId);
            return new ResponseEntity<>("Prenotazione eliminata con successo.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}