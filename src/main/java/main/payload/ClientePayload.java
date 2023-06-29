package main.payload;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import main.enums.Role;

@Data
public class ClientePayload {

    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String cognome;

    @NotBlank
    private String numeroTelefono;

    @NotBlank
    private String password;

    @NotNull
    private Role role;

}
