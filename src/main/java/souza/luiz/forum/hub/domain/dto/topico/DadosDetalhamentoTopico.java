package souza.luiz.forum.hub.domain.dto.topico;

import souza.luiz.forum.hub.domain.model.topico.Status;
import souza.luiz.forum.hub.domain.model.topico.Topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        Status status,
        Long idAutor,
        Long idCurso
) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatusTopico(),
                topico.getAutor().getId(),
                topico.getCurso().getId()
        );
    }
}
