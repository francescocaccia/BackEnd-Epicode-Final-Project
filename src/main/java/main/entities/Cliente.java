package main.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import main.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
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

    @Column
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
    private Set<Recensione> recensioni = new HashSet<>();

    @Column
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
    private Set<Prenotazione> prenotazioni = new HashSet<>();


    @Column
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
    private final Set<Ristorante> ristoranti = new HashSet<>();

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

    public void addRestaurant(List<Ristorante> ristoranti) {
        Optional.ofNullable(ristoranti)
                .ifPresent(it -> {
                    this.ristoranti.addAll(ristoranti);
                    this.ristoranti.forEach(r -> r.setCliente(this));
                });
    }

    public void addPrenotazioni (List<Prenotazione> prenotazioni){
        Optional.ofNullable(prenotazioni)
                .ifPresent(it ->{
                    this.prenotazioni.addAll(prenotazioni);
                    this.prenotazioni.forEach(p -> p.setCliente(this));
                });
    }

    public void addRecensioni (List<Recensione> recensioni){

        Optional.ofNullable(recensioni)
                .ifPresent(it ->{
                    this.recensioni.addAll(recensioni);
                    this.recensioni.forEach(p -> p.setCliente(this));
                });

    }
}
