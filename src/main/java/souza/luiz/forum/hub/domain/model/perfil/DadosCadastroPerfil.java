package souza.luiz.forum.hub.domain.model.perfil;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPerfil(
        @NotNull TipoPerfil nome
) {
}
