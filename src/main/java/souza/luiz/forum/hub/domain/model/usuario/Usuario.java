package souza.luiz.forum.hub.domain.model.usuario;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import souza.luiz.forum.hub.domain.dto.usuario.DadosAtualizacaoUsuario;
import souza.luiz.forum.hub.domain.dto.usuario.DadosCadastroUsuario;
import souza.luiz.forum.hub.domain.model.ModelInterface;
import souza.luiz.forum.hub.domain.model.perfil.Perfil;
import souza.luiz.forum.hub.domain.repository.PerfilRepository;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "id")
public class Usuario implements ModelInterface {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
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
    }


    @Override
    public void ativar() {
        this.ativo = true;
    }

    @Override
    public void desativar() {
        this.ativo = false;
    }

    public void atualizar(@Valid DadosAtualizacaoUsuario dados) {
        if(dados.nome() != null) this.nome = dados.nome();
        if(dados.email() != null) this.email = dados.email();
        if(dados.senha() != null) this.nome = dados.senha();
    }
}
