package souza.luiz.forum.hub.domain.model.resposta;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import souza.luiz.forum.hub.domain.dto.resposta.DadosAtualizacaoResposta;
import souza.luiz.forum.hub.domain.dto.resposta.DadosCadastroResposta;
import souza.luiz.forum.hub.domain.model.topico.Topico;
import souza.luiz.forum.hub.domain.model.usuario.Usuario;

import java.time.LocalDateTime;

@Entity
@Table(name = "respostas")
@Getter
@Setter
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
    private boolean solucao;
    private boolean ativo;

    public Resposta(@Valid DadosCadastroResposta dados) {
        this.mensagem = dados.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.solucao = false;
        this.ativo = true;
    }


    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public void atualizar(@Valid DadosAtualizacaoResposta dados) {
        this.mensagem = dados.mensagem();
    }

    public void responder(){
        this.solucao = true;
    }
}
