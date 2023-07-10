package main.controller;

import main.entities.Ristorante;
import main.enums.TipoCucina;
import main.payload.RistorantePayload;
import main.service.RistoranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static main.enums.Role.ADMIN;

@RestController
@RequestMapping("/ristoranti")
public class RistoranteController {

    @Autowired
    private RistoranteService ristoranteService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
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


    @GetMapping("/{id}")
    public ResponseEntity<Ristorante> getById(@PathVariable("id") Long id) {
        Optional<Ristorante> ristoranteOptional = ristoranteService.getById(id);

        if (ristoranteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Ristorante ristorante = ristoranteOptional.get();
        return ResponseEntity.ok(ristorante);
    }


    @GetMapping("/tipo-cucina/{tipoCucina}")
    public ResponseEntity<List<Ristorante>> getByTipoCucina(@PathVariable("tipoCucina") TipoCucina tipoCucina) {
        List<Ristorante> response = ristoranteService.getByTipoCucina(tipoCucina);

        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/nome/{nomeRistorante}")
    public ResponseEntity<List<Ristorante>> getRistoranteByNomeRistorante(@PathVariable String nomeRistorante) {
        List<Ristorante> ristorante = ristoranteService.findRistoranteByNomeRistorante(nomeRistorante);
        if (ristorante != null) {
            return ResponseEntity.ok(ristorante);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/luogo/citta/{citta}")
    public ResponseEntity<List<Ristorante>> getRistoranteByCitta(@PathVariable String citta){
        List<Ristorante> ristoranti = ristoranteService.findByLuogo(citta);
        return ResponseEntity.ok(ristoranti);
    }



    @GetMapping("/?citta=NomeCitta&tipoCucina=italiana")
    public ResponseEntity<List<Ristorante>> ricercaRistoranti(
            @RequestParam(required = true) String citta,
            @RequestParam(required = false) TipoCucina tipoCucina,
            @RequestParam(required = false) String nomeRistorante
    ) {
        List<Ristorante> ristoranti = ristoranteService.ricercaRistoranti(citta, tipoCucina, nomeRistorante);

        if (ristoranti.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ristoranti);
    }




    @GetMapping("/cerca")
    public ResponseEntity<List<Ristorante>> searchRestaurants(@RequestParam(value = "perStringa") String value) {
        List<Ristorante> ristorantes = ristoranteService.searchByTipoCucinaOrNomeRistorante(value);
        return new ResponseEntity<>(ristorantes, HttpStatus.OK);
    }






}

