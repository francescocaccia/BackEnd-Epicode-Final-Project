package main.controller;

import main.entities.Ristorante;
import main.payload.RistorantePayload;
import main.service.RistoranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ristoranti")
public class RistoranteController {

    @Autowired
    private RistoranteService ristoranteService;

    @PostMapping("/create")
    public ResponseEntity<Void> aggiungiRistorante(@RequestBody RistorantePayload ristorantePayload) {
        ristoranteService.inserisciRistorante(ristorantePayload);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminaRistorante(@PathVariable("id") Long id) {
        ristoranteService.eliminaRistorante(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ristorante>> getAll() {
        List<Ristorante> response = ristoranteService.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // implementa get by id
    // implementa get by tipo cucina





}
