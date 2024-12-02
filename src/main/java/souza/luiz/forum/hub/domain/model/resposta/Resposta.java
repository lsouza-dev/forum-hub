package souza.luiz.forum.hub.domain.model.resposta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import souza.luiz.forum.hub.domain.model.topico.Topico;
import souza.luiz.forum.hub.domain.model.usuario.Usuario;

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
    @JoinColumn(name = "idTopico")
    private Topico topico;
    private LocalDateTime dataCriacao;
    @ManyToOne()
    @JoinColumn(name = "idAutor")
    private Usuario autor;
    private String solucao;
    private boolean ativo;


    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

}
