package main.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "piatto")
public class Piatto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nomePiatto;

    @Column
    private Double prezzo;

    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu menu;
}
