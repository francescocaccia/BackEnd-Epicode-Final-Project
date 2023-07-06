package main.service;

import lombok.extern.slf4j.Slf4j;
import main.entities.Luogo;
import main.repository.LuogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LuogoService {
    @Autowired
    LuogoRepository luogoRepository;


    public List<String> findCitta() {
        log.info("cerco tutte le citta presenti");
        List<Luogo> luoghi = luogoRepository.findAll();

        List<String> citta = luoghi.stream().map(Luogo::getCitta).collect(Collectors.toList());

        log.info("citta trovate:" + citta);
        return citta;

    }
}
