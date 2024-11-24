package souza.luiz.forum.hub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import souza.luiz.forum.hub.domain.curso.Curso;
import souza.luiz.forum.hub.domain.resposta.Resposta;
import souza.luiz.forum.hub.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private Status statusTopico;
    @ManyToOne()
    private Usuario autor;
    @ManyToOne()
    private Curso curso;
    @OneToMany(mappedBy = "topico",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Resposta> respostas = new ArrayList<>();
}
