package main.service;

import main.repository.RistoranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RistoranteService {

    @Autowired
    private RistoranteRepository ristoranteRepository;

    public void inserisciRistorante() {
    }
}
