package main.payload;

import lombok.Data;
import main.entities.Cliente;
import main.enums.Role;

import java.util.List;

@Data
public class ClienteResponse {

    private String nome;
    private String cognome;
    private String email;
    private String numeroTelefono;
    private Long id;
    private Role role;
    private List<RistoranteResponse> ristorante;


    public static ClienteResponse creaResponse(Cliente cliente) {
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(cliente.getIdCliente());
        clienteResponse.setEmail(cliente.getEmail());
        clienteResponse.setNome(cliente.getNome());
        clienteResponse.setCognome(cliente.getCognome());
        clienteResponse.setNumeroTelefono(cliente.getNumeroTelefono());
        clienteResponse.setRole(cliente.getRole());
        List<RistoranteResponse> list = cliente.getRistoranti().stream().map(ristorante -> new RistoranteResponse(ristorante.getIdRistorante(), ristorante.getNomeRistorante())).toList();
        clienteResponse.setRistorante(list);
        return clienteResponse;
    }
}
