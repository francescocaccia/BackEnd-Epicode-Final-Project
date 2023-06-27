package main.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import main.Enums.TipoCucina;

@Getter
@Setter
@Entity
@Table(name = "ristorante")
public class Ristorante {

    private int coperti;
    private String recensioni;
    private TipoCucina tipoCucina;
    private String indirizzo;
    private String menu;
    private Citta citta;
}
