package main.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import main.entities.CardImmagini;
import main.enums.TipoCucina;

@Data
public class RistorantePayload {

    @Min(value = 5)
    private int totaleCoperti;

    @NotNull(message = "il tipo di cucina è obbligatorio")
    private TipoCucina tipoCucina;

    @NotNull(message = "il menu è obbligatorio")
    private MenuPayload menu;

    @NotNull(message = "il luogo è obbligatorio")
    private LuogoPayload luogo;

    @NotNull(message = "almeno un'immagine è obbligatoria")
    private CardImmaginiPayload cardImmagini;


}
