package main.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecensionePayload {


    @NotNull(message = "scegliere un numero di stelle per recensire è obbligatorio")
    private int numeroStelle;

    @NotNull(message = "dare un titolo alla recensione è obbligatorio")
    private String titoloRecensione;

    @NotNull(message = "aggiungere un contenuto per recensire è obbligatorio")
    private String contenutoRecensione;

}
