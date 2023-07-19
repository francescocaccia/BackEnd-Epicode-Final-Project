package main.controller;

import main.entities.Recensione;
import main.payload.RecensionePayload;
import main.service.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/recensioni")
public class RecensioneController {

    @Autowired
    RecensioneService recensioneService;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> aggiungiRecensione(@RequestBody RecensionePayload recensionePayload) {
        try {
            recensioneService.addRecensione(recensionePayload);
            return new ResponseEntity<>("recensione aggiunta", HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @DeleteMapping("/delete/{idCliente}/{idRecensione}")
    public ResponseEntity<String> eliminaRecensione(@PathVariable("idCliente") Long idCliente, @PathVariable("idRecensione") Long idRecensione) {
        try {
            recensioneService.deleteRecensione(idRecensione, idCliente);
            return new ResponseEntity<>("recensione eliminata con successo", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/search/{idRistorante}")
    public Set<Recensione> getRecensioniByRistoranteId(@PathVariable Long idRistorante) {
        return recensioneService.getRecensioniByRistoranteId(idRistorante);
    }

    @GetMapping("/utente/{idCliente}")
    public ResponseEntity<List<Recensione>> getRecensioniByUtenteId(@PathVariable Long idCliente) {
        List<Recensione> recensioni = recensioneService.getRecensioniByClienteId(idCliente);
        return new ResponseEntity<>(recensioni, HttpStatus.OK);
    }

}


