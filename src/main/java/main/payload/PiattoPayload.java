package main.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PiattoPayload {

    @NotBlank(message = "il nome del piatto è obbligatorio")
    private String nomePiatto;

    @NotNull(message = "il prezzo è obbligatorio")
    private Double prezzo;

    
}
