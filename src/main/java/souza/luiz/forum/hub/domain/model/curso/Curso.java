package souza.luiz.forum.hub.domain.model.curso;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import souza.luiz.forum.hub.domain.dto.curso.DadosAtualizacaoCurso;
import souza.luiz.forum.hub.domain.dto.curso.DadosCadastroCurso;

@Entity
@Table(name = "cursos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private boolean ativo;

    public Curso(@Valid DadosCadastroCurso dados) {
        this.nome = dados.nome();
        this.categoria = dados.categoria();
        this.ativo = true;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public void atualizar(@Valid DadosAtualizacaoCurso dados) {
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }
}
