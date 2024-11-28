package souza.luiz.forum.hub.domain.dto.perfil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import souza.luiz.forum.hub.domain.model.perfil.TipoPerfil;

public record DadosAtualizacaoPerfil(
        @NotNull Long id,
        @NotBlank TipoPerfil nome
        ) {
}
