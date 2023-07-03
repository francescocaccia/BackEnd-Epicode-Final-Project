package main.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardImmaginiPayload {

    @NotNull(message = "è obbligatorio inserire almeno un immagine")
    private String immagine1;

    private String immagine2;

    private String immagine3;

    public CardImmaginiPayload(String immagine1) {
        this.immagine1 = immagine1;
    }

    public CardImmaginiPayload(String immagine1, String immagine2) {
        this.immagine1 = immagine1;
        this.immagine2 = immagine2;
    }

    public CardImmaginiPayload(String immagine1, String immagine2, String immagine3) {
        this.immagine1 = immagine1;
        this.immagine2 = immagine2;
        this.immagine3 = immagine3;
    }

}
