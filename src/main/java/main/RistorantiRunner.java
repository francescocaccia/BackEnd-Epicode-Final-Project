/*
package main;

import com.github.javafaker.Faker;
import main.entities.*;
import main.enums.TipoCucina;
import main.repository.LuogoRepository;
import main.repository.RecensioneRepository;
import main.repository.RistoranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class RistorantiRunner implements CommandLineRunner {

    @Autowired
    RistoranteRepository ristoranteRepository;

    @Autowired
    RecensioneRepository recensioneRepository;

    @Autowired
    LuogoRepository luogoRepository;

    @Autowired
    ImmaginiRepository immaginiRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("it"));

        // Verifica se ci sono già dei ristoranti nel database
        if (ristoranteRepository.count() == 0) {
            // Crea 10 ristoranti di esempio
            for (int i = 0; i < 10; i++) {
                Ristorante ristorante = new Ristorante();
                ristorante.setNomeRistorante(faker.company().name());
                ristorante.setTotaleCoperti(faker.number().numberBetween(5, 100));
                ristorante.setTipoCucina(TipoCucina.values()[faker.number().numberBetween(0, TipoCucina.values().length)]);

                // Creazione delle recensioni per il ristorante
                int numRecensioni = faker.number().numberBetween(0, 5);
                List<Recensione> recensioni = new ArrayList<>();
                for (int j = 0; j < numRecensioni; j++) {
                    Recensione recensione = new Recensione();
                    recensione.setNumeroStelle(faker.number().numberBetween(1, 5));
                    recensione.setTitoloRecensione("strung hgfgrtr");
                    Cliente cliente = new Cliente();
                    cliente.setEmail("cliente@jguty");
                    cliente.setNome("gabibbo");
                    cliente.setCognome("ajeje");
                    cliente.setNumeroTelefono("3393939393");
                    recensione.setNomeAutore(cliente);

                    recensione.setContenutoRecensione(faker.lorem().paragraph());
                    recensione.setDataRecensione(faker.date().birthday());

                    // ... altre impostazioni per la recensione
                    recensioneRepository.save(recensione);
                    recensioni.add(recensione);
                }
                ristorante.setRecensione(recensioni);

                // Creazione del luogo per il ristorante
                Luogo luogo = new Luogo();
                luogo.setIndirizzo(faker.address().streetAddress());
                luogo.setCitta(faker.address().city());
                // ... altre impostazioni per il luogo
                luogoRepository.save(luogo);
                ristorante.setLuogo(luogo);

                // Creazione delle immagini per il ristorante
                int numImmagini = faker.number().numberBetween(1, 3);
                List<CardImmagini> immaginiList = new ArrayList<>();
                for (int k = 0; k < numImmagini; k++) {
                    CardImmagini immagini = new CardImmagini();
                    immagini.setUrl(faker.internet().image());
                    // ... altre impostazioni per l'immagine
                    immaginiRepository.save(immagini);
                    immaginiList.add(immagini);
                }
                ristorante.setCardImmagini(immaginiList);

                // ... altre impostazioni per il ristorante

                ristoranteRepository.save(ristorante);
            }
        }
    }
}
*/
