package main.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecensionePayload {

    @Min(value = 1)
    @Max(value = 5)
    @NotNull(message = "scegliere un numero di stelle per recensire è obbligatorio")
    private int numeroStelle;

    @NotNull(message = "aggiungere un contenuto per recensire è obbligatorio")
    private String contenutoRecensione;

    @NotNull
    private Long idCliente;
    @NotNull
    private Long idRistorante;

}
