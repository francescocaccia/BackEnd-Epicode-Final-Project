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
    private List<PrenotazioneResponse> prenotazioni;

    public static ClienteResponse creaResponse(Cliente cliente) {
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(cliente.getIdCliente());
        clienteResponse.setEmail(cliente.getEmail());
        clienteResponse.setNome(cliente.getNome());
        clienteResponse.setCognome(cliente.getCognome());
        clienteResponse.setNumeroTelefono(cliente.getNumeroTelefono());
        clienteResponse.setRole(cliente.getRole());
        List<RistoranteResponse> list = cliente.getRistoranti().stream().map(ristorante -> new RistoranteResponse(ristorante.getIdRistorante(), ristorante.getNomeRistorante())).toList();
        List<PrenotazioneResponse> listaP = cliente.getPrenotazioni().stream().map(prenotazione -> new PrenotazioneResponse(prenotazione.getIdPrenotazione(), prenotazione.getDataPrenotazione(), prenotazione.getNumeroPersone(), prenotazione.getRistorante().getNomeRistorante())).toList();
        clienteResponse.setRistorante(list);
        clienteResponse.setPrenotazioni(listaP);
        return clienteResponse;
    }
}
