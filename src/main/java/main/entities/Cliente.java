package main.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import main.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties({ "ristoranteDiProprietario"})
@Entity
@Table(name = "cliente")
public class Cliente implements UserDetails {

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
    private String password;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRecensione")
    private List<Recensione> recensioni;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPrenotazione")
    private List<Prenotazione> prenotazioni;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ristorante di proprietario")
    private Ristorante ristoranteDiProprietario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getPassword() {

        return this.password;
    }

//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }
}
