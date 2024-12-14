package souza.luiz.forum.hub.domain.dto.resposta;

import souza.luiz.forum.hub.domain.dto.topico.DadosDetalhamentoTopico;
import souza.luiz.forum.hub.domain.dto.usuario.DadosDetalhamentoUsuario;
import souza.luiz.forum.hub.domain.model.resposta.Resposta;
import souza.luiz.forum.hub.domain.model.topico.Topico;
import souza.luiz.forum.hub.domain.model.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(
        Long id,
        String mensagem,
        DadosDetalhamentoTopico topico,
        LocalDateTime dataCriacao,
        DadosDetalhamentoUsuario autor,
        boolean solucao
) {
    public DadosDetalhamentoResposta(Resposta r) {
        this(
                r.getId(),
                r.getMensagem(),
                new DadosDetalhamentoTopico(r.getTopico()),
                r.getDataCriacao(),
                new DadosDetalhamentoUsuario(r.getAutor()),
                r.isSolucao()
        );
    }
}
