package main.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import main.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column
    private String email;

    @Column
    private String nome;

    @Column
    private String cognome;

    @Column
    private String numeroTelefono;

    @Column
    // TODO ricorda: la password non deve essere in chiaro
    private String password;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany
    @JoinColumn(name = "idRecensione")
    private List<Recensione> recensioni;

    @OneToMany
    @JoinColumn(name = "idPrenotazione")
    private List<Prenotazione> prenotazioni;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
