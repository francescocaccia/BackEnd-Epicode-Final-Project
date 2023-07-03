package main.controller;

import main.entities.Cliente;
import main.payload.ClientePayload;
import main.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;



    @GetMapping("/me")
//    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public Cliente getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return clienteService.findByEmail(email);
    }



    @PostMapping("")
    public ResponseEntity<String> creaCliente(@RequestBody ClientePayload clientePayload) {

        clienteService.creaCliente(clientePayload);
        ClienteService clienteService1 = new ClienteService();
        return new ResponseEntity<>("cliente creato", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {

        clienteService.deleteCliente(id);
        return new ResponseEntity<>("cliente eliminato", HttpStatus.NO_CONTENT);

    }

    @PutMapping("/modifica/{id}")
    public ResponseEntity<String> modificaCliente(@PathVariable Long id, @RequestBody ClientePayload clientePayload) {

        clienteService.modificaCliente(id, clientePayload);
        return new ResponseEntity<>("cliente modificato", HttpStatus.NO_CONTENT);
    }

}
