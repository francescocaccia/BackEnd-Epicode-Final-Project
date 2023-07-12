package main.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class PrenotazionePayload {

    @NotNull(message = "La data della prenotazione è obbligatoria")
    private Date dataPrenotazione;

    @Positive
    private int numeroPersone;

    @NotNull(message = "l'id cliente è obbligatorio")
    private Long idCliente;

    @NotNull(message = "l'id ristorante è obbligatorio")
    private Long idRistorante;


}

