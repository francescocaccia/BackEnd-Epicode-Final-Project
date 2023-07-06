package main.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "luogo")
public class Luogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLuogo;

    @Column
    private String regione;

    @Column
    private String citta;

    @Column
    private String indirizzo;
    @Column
    private String numeroCivico;

    @OneToOne
    @JoinColumn(name = "idRistorante")
    private Ristorante ristorante;

}
