package souza.luiz.forum.hub.domain.dto.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoResposta(
        @NotNull Long idResposta,
        @NotBlank String mensagem
) {
}
