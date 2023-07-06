package main.controller;

import main.entities.Ristorante;
import main.enums.TipoCucina;
import main.payload.RistorantePayload;
import main.service.RistoranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    @GetMapping("/{id}")
    public ResponseEntity<Ristorante> getById(@PathVariable("id") Long id) {
        Optional<Ristorante> ristoranteOptional = ristoranteService.getById(id);

        if (ristoranteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Ristorante ristorante = ristoranteOptional.get();
        return ResponseEntity.ok(ristorante);
    }


    @GetMapping("/tipo-cucina")
    public ResponseEntity<List<Ristorante>> getByTipoCucina(@RequestParam("tipoCucina") TipoCucina tipoCucina) {
        List<Ristorante> response = ristoranteService.getByTipoCucina(tipoCucina);

        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
//    @GetMapping("/restaurants")
//    public ResponseEntity<List<Ristorante>> searchRestaurants(@RequestParam(value = "citta", required = false) String city,
//                                                              @RequestParam(value = "nomeRistornate", required = false) String restaurantName) {
//        List<Ristorante> response = ristoranteService.searchRestaurants(city, restaurantName);
//
//        if (response.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }



    }

