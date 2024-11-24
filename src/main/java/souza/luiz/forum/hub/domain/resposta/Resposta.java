package souza.luiz.forum.hub.domain.resposta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import souza.luiz.forum.hub.domain.topico.Topico;
import souza.luiz.forum.hub.domain.usuario.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "respostas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    @ManyToOne()
    private Topico topico;
    private LocalDateTime dataCriacao;
    @ManyToOne()
    private Usuario autor;
    private String solucao;
}
