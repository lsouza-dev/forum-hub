package souza.luiz.forum.hub.domain.model.perfil;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import souza.luiz.forum.hub.domain.dto.perfil.DadosAtualizacaoPerfil;
import souza.luiz.forum.hub.domain.model.ModelInterface;

@Entity
@Table(name = "perfis")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil implements ModelInterface {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private TipoPerfil nome;
    private boolean ativo;

    public Perfil(@Valid DadosCadastroPerfil dados) {
        this.nome = dados.nome();
        ativo = true;
    }

    @Override
    public void ativar() {
        this.ativo = true;
    }

    @Override
    public void desativar() {
        this.ativo = false;
    }


    public void atualizar(DadosAtualizacaoPerfil dados) {
        this.nome = dados.nome();
    }
}
