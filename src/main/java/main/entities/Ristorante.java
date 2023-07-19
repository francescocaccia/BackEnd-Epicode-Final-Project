package main.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import main.enums.TipoCucina;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "ristorante")
public class Ristorante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRistorante;

    @Column(nullable = false)
    private String nomeRistorante;

    @Column(nullable = false)
    private int totaleCoperti;

    @OneToMany(mappedBy = "ristorante")
//    @JsonIgnore
    private Set<Recensione> recensione = new HashSet<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCucina tipoCucina;

    @OneToOne
    @JoinColumn(name = "idMenu")
    private Menu menu;

    @OneToOne
    @JoinColumn(name = "idLuogo")
    private Luogo luogo;

    @OneToMany(mappedBy = "ristorante")
    @JsonIgnore
    private Set<Prenotazione> prenotazioni = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "idCard")
    private CardImmagini cardImmagini;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "idCliente")
    private Cliente cliente;



    public void addPrenotazioni (List<Prenotazione> prenotazioni){
        Optional.ofNullable(prenotazioni)
                .ifPresent(it ->{
                    this.prenotazioni.addAll(prenotazioni);
                    this.prenotazioni.forEach(p -> p.setRistorante(this));
                });
    }


    public void addRecensioni (List<Recensione> recensioni){

        Optional.ofNullable(recensioni)
                .ifPresent(it ->{
                    this.recensione.addAll(recensioni);
                    this.recensione.forEach(p -> p.setRistorante(this));
                });
    }
}
