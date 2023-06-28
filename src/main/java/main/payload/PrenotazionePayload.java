package main.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PrenotazionePayload {

    @NotNull(message = "La data della prenotazione è obbligatoria")
    private String dataPrenotazione;

    @Positive
    private int numeroPersone;

    private Long idCliente;

    private Long idRistorante;


}

