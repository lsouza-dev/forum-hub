package souza.luiz.forum.hub.domain.model.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.query.internal.ModelPartResultMementoBasicImpl;
import souza.luiz.forum.hub.domain.dto.topico.DadosAtualizacaoTopico;
import souza.luiz.forum.hub.domain.dto.topico.DadosCadastroTopico;
import souza.luiz.forum.hub.domain.model.ModelInterface;
import souza.luiz.forum.hub.domain.model.curso.Curso;
import souza.luiz.forum.hub.domain.model.resposta.Resposta;
import souza.luiz.forum.hub.domain.model.usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico implements ModelInterface {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    private String mensagem;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_topico")
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

    public Topico(@Valid DadosCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.statusTopico = Status.NAO_RESPONDIDO;
        this.dataCriacao = LocalDateTime.now();
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

    public void atualizar(@Valid DadosAtualizacaoTopico d){
        if(d.titulo() != null) this.titulo = d.titulo();
        if(d.mensagem() != null) this.mensagem = d.mensagem();
        if(d.status_topico() != null) this.statusTopico = d.status_topico();
    }

    @Override
    public String toString() {
        return "Topico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", mensagem='" + mensagem + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", statusTopico=" + statusTopico +
                ", autor=" + autor.getId() +
                ", curso=" + curso.getId() +
                ", respostas=" + respostas +
                ", ativo=" + ativo +
                '}';
    }
}
