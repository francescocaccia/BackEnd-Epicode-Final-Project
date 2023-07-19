package main.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String contenutoRecensione;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idCliente")
    private Cliente cliente;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idRistorante")
    private Ristorante ristorante;

}
