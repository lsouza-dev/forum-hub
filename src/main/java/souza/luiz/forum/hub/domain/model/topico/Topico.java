package souza.luiz.forum.hub.domain.model.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.query.internal.ModelPartResultMementoBasicImpl;
import souza.luiz.forum.hub.domain.model.ModelInterface;
import souza.luiz.forum.hub.domain.model.curso.Curso;
import souza.luiz.forum.hub.domain.model.resposta.Resposta;
import souza.luiz.forum.hub.domain.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico implements ModelInterface {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private Status statusTopico;
    @ManyToOne()
    @JoinColumn(name = "idAutor")
    private Usuario autor;
    @ManyToOne()
    @JoinColumn(name = "idCurso")
    private Curso curso;
    @OneToMany(mappedBy = "topico",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Resposta> respostas = new ArrayList<>();
    private boolean ativo;


    @Override
    public void ativar() {
        this.ativo = true;
    }

    @Override
    public void desativar() {
        this.ativo = false;
    }

}
