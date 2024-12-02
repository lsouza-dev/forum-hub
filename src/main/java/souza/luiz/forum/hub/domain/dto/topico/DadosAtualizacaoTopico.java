package souza.luiz.forum.hub.domain.dto.topico;

import jakarta.validation.constraints.NotNull;
import souza.luiz.forum.hub.domain.model.topico.Status;

public record DadosAtualizacaoTopico(
        @NotNull Long id,
        String titulo,
        String mensagem,
        Status status_topico,
        Long idAutor,
        Long idCurso
) {
}
