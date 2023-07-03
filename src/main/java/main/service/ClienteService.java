package main.service;

import lombok.extern.slf4j.Slf4j;
import main.entities.Cliente;
import main.exception.ClienteAlreadyPresentException;
import main.payload.ClientePayload;
import main.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente creaCliente(ClientePayload clientePayload) {

        log.info("Provo ad aggiungere il cliente {}", clientePayload);

        Optional<Cliente> cliente = clienteRepository.findByEmail(clientePayload.getEmail());

        if (cliente.isPresent()) {
            log.error("Errore nell'aggiunta del cliente, email {} già presente", clientePayload.getEmail());
            throw new ClienteAlreadyPresentException();
        }
        Cliente cliente1 = new Cliente();
        cliente1.setNome(clientePayload.getNome());
        cliente1.setCognome(clientePayload.getCognome());
        cliente1.setEmail(clientePayload.getEmail());
        cliente1.setNumeroTelefono(clientePayload.getNumeroTelefono());
        cliente1.setPassword(clientePayload.getPassword());
        cliente1.setRole(clientePayload.getRole());


        clienteRepository.save(cliente1);

        log.info("Cliente aggiunto con successo");

        return cliente1;
    }


    public void modificaCliente(Long id, ClientePayload clientePayload) {

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {

            Cliente getCliente = cliente.get();
            getCliente.setNome(clientePayload.getNome());
            getCliente.setCognome(clientePayload.getCognome());
            getCliente.setEmail(clientePayload.getEmail());
            getCliente.setNumeroTelefono(clientePayload.getNumeroTelefono());
            getCliente.setPassword(clientePayload.getPassword());
            clienteRepository.save(getCliente);

        } else {
            throw new RuntimeException("Esiste già un Cliente con la stessa e-mail");
        }

    }

    public void deleteCliente(Long id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {

            clienteRepository.deleteById(id);
        }
    }


    public Cliente clienteLogin(String email, String password) {

        Optional<Cliente> cliente = clienteRepository.findByEmailAndPassword(email, password);

        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new RuntimeException("le credenziali inserite sono errate");
        }

    }

    public Cliente findByEmail(String email) {

        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new RuntimeException("utente non trovato");
        }

    }
}



