package main.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CardImmaginiPayload {

    @NotNull(message = "è obbligatorio inserire almeno un immagine")
    private byte[] immagine1;

    private byte[] immagine2;

    private byte[] immagine3;
}
