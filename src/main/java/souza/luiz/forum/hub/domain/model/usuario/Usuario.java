package souza.luiz.forum.hub.domain.model.usuario;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import souza.luiz.forum.hub.domain.dto.usuario.DadosAtualizacaoUsuario;
import souza.luiz.forum.hub.domain.dto.usuario.DadosCadastroUsuario;
import souza.luiz.forum.hub.domain.model.perfil.Perfil;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "id")
public class Usuario implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String login;
    @Column(name = "senha")
    private String senha;
    @ManyToOne()
    @JoinColumn(name = "idPerfil")
    private Perfil perfil;
    private boolean ativo;

    public Usuario(@Valid DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.ativo = true;
        this.login = this.email;
    }


    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public void atualizar(@Valid DadosAtualizacaoUsuario dados) {
        if(dados.nome() != null) this.nome = dados.nome();
        if(dados.email() != null) this.email = dados.email();
        if(dados.senha() != null) this.nome = dados.senha();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + senha + '\'' +
                ", perfil=" + perfil +
                ", ativo=" + ativo +
                '}';
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }


    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
