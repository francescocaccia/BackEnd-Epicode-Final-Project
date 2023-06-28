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

    @Column
    private byte[] immagine1;

    @Column(nullable = true)
    private byte[] immagine2;

    @Column(nullable = true)
    private byte[] immagine3;
}
