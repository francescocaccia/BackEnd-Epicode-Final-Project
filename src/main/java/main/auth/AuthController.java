//package main.auth;
//
//import main.auth.payloads.AuthenticationSuccessfullPayload;
//import main.entities.Cliente;
//import main.exception.NotFoundException;
//import main.exception.UnauthorizedException;
//import main.payload.ClientePayload;
//import main.service.ClienteService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    ClienteService clienteService;
//
//
//    @Autowired
//    private PasswordEncoder bcrypt;
//
//    @PostMapping("/register")
//    public ResponseEntity<Cliente> register(@RequestBody @Validated ClientePayload body) {
//
//        body.setPassword(bcrypt.encode(body.getPassword()));
//
//        Cliente createdCliente = clienteService.creaCliente(body);
//
//        return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody ClientePayload body)
//            throws NotFoundException {
//
//        Cliente user = clienteService.findByEmail(body.getEmail());
//
//        String plainPW = body.getPassword();
//        String hashedPW = user.getPassword();
//
//        if (!bcrypt.matches(plainPW, hashedPW))
//            throw new UnauthorizedException("Credenziali non valide!");
//
//        String token = JWTTools.createToken(user);
//
//        return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
//    }
//
//}