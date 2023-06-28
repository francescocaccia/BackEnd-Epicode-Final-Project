package main.payload;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LuogoPayload {

    @NotBlank
    private String regione;

    @NotBlank
    private String citta;

    @NotBlank
    private String indirizzo;
    @NotBlank
    private String numeroCivico;
}
