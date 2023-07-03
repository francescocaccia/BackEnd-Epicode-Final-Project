package main.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "card_immagini")
public class CardImmagini {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCard;

    @Column(nullable = false)
    private String immagine1;

    @Column
    private String immagine2;

    @Column
    private String immagine3;

    @OneToOne
    @JoinColumn(name = "idRistorante")
    private Ristorante ristorante;
}
