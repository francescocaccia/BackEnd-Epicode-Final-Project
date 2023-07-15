package main.service;

import main.entities.Cliente;
import main.entities.Recensione;
import main.entities.Ristorante;
import main.payload.RecensionePayload;
import main.repository.ClienteRepository;
import main.repository.RecensioneRepository;
import main.repository.RistoranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecensioneService {


    @Autowired
    RecensioneRepository recensioneRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    RistoranteService ristoranteService;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    RistoranteRepository ristoranteRepository;

    public void addRecensione(RecensionePayload recensionePayload) {

        //controllo che il cliente non abbia già una recensione  nello stesso ristorante

        Cliente cliente = clienteService.findById(recensionePayload.getIdCliente());

        Optional<Ristorante> ristorante = ristoranteService.getById(recensionePayload.getIdRistorante());
        Ristorante ristoranteRecensito;
        if (ristorante.isPresent()) {
            ristoranteRecensito = ristorante.get();
        } else {
            throw new RuntimeException("Ristorante non presente");
        }

        Optional<Recensione> recensione = cliente.getRecensioni().stream().filter(r -> r.getRistorante().getIdRistorante().equals(recensionePayload.getIdRistorante())).findAny();

        if (recensione.isPresent()) {

            throw new RuntimeException("hai già una recensione per questo ristorante");
        }

        Recensione nuovaRecensione = new Recensione();
        nuovaRecensione.setContenutoRecensione(recensionePayload.getContenutoRecensione());
        nuovaRecensione.setNumeroStelle(recensionePayload.getNumeroStelle());
        nuovaRecensione.setCliente(cliente);
        nuovaRecensione.setRistorante(ristoranteRecensito);

        recensioneRepository.save(nuovaRecensione);

        cliente.addRecensioni(List.of(nuovaRecensione));
        clienteRepository.save(cliente);

        ristoranteRecensito.addRecensioni(List.of(nuovaRecensione));
        ristoranteRepository.save(ristoranteRecensito);
    }


    public void deleteRecensione(Long idRecensione, Long idCliente) {

        Cliente cliente = clienteService.findById(idCliente);
        Optional<Recensione> recensione =
        cliente.getRecensioni().stream().filter(r -> r.getIdRecensione().equals(idRecensione)).findAny();
        if (recensione.isPresent()) {
//            recensioneRepository.deleteById(idRecensione);
            recensioneRepository.delete(recensione.get());
        } else {
            throw new RuntimeException("non puoi cancellare questa recensione");
        }

    }

}
