package main.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CardImmaginiPayload {

    @NotNull(message = "è obbligatorio inserire almeno un immagine")
    private String immagine1;

    private String immagine2;

    private String immagine3;
}
