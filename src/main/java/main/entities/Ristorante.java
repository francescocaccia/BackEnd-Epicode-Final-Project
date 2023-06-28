package main.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import main.enums.TipoCucina;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ristorante")
public class Ristorante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRistorante;

    @Column(nullable = false)
    private String nomeRistorante;

    @Column(nullable = false)
    private int totaleCoperti;

    @OneToMany
    @JoinColumn(name = "idRecensione")
    private List<Recensione> recensione;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCucina tipoCucina;

    @OneToOne
    @JoinColumn(name = "idMenu")
    private Menu menu;

    @OneToOne
    @JoinColumn(name = "idLuogo")
    private Luogo luogo;

    @OneToMany
    @JoinColumn(name = "idPrenotazione")
    private List<Prenotazione> prenotazioni;

    @OneToOne
    @JoinColumn(name = "idCard")
    private CardImmagini cardImmagini;
}
