package main.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "recensione")
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecensione;

    @Column
    private int numeroStelle;

    @Column
    private String titoloRecensione;

    @Column
    private String contenutoRecensione;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente nomeAutore;

    @Column
    private Date dataRecensione;

    @ManyToOne
    @JoinColumn(name = "idRistorante")
    private Ristorante ristorante;

}