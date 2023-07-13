package main.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class PrenotazioneResponse {
    private  Long idPrenotazione;

    private Date dataPrenotazione;

    private int numeroPersone;

    private String nomeRistorante;

    private  Long idRistorante;
}
