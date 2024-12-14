package souza.luiz.forum.hub.domain.dto.topico;

import souza.luiz.forum.hub.domain.dto.curso.DadosDetalhamentoCurso;
import souza.luiz.forum.hub.domain.dto.resposta.DadosDetalhamentoResposta;
import souza.luiz.forum.hub.domain.dto.usuario.DadosDetalhamentoUsuario;
import souza.luiz.forum.hub.domain.model.resposta.Resposta;
import souza.luiz.forum.hub.domain.model.topico.Status;
import souza.luiz.forum.hub.domain.model.topico.Topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopicoRespondido(
        Long id,
        String tiulo,
        String mensagem,
        LocalDateTime dataCriacao,
        Status status,
        DadosDetalhamentoUsuario autor,
        DadosDetalhamentoCurso curso,
        DadosDetalhamentoResposta resposta
) {
    public DadosDetalhamentoTopicoRespondido(Topico t, Resposta r) {
        this(
                t.getId(),
                t.getTitulo(),
                t.getMensagem(),
                t.getDataCriacao(),
                t.getStatusTopico(),
                new DadosDetalhamentoUsuario(t.getAutor()),
                new DadosDetalhamentoCurso(t.getCurso()),
                new DadosDetalhamentoResposta(r)
        );
    }
}
