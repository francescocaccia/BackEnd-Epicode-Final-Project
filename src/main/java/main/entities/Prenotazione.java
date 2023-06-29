package main.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "prenotazione")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrenotazione;

    @Column(nullable = false)
    private Date dataPrenotazione;

    @Column
    private int numeroPersone;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idRistorante")
    private Ristorante ristorante;


    public Prenotazione(Cliente cliente, Ristorante ristorante, Date date, int numeroPersone) {
    }


}
