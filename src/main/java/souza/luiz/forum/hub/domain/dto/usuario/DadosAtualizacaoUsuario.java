package souza.luiz.forum.hub.domain.dto.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(
        @NotNull Long id,
        String nome,
        String email,
        String senha,
        Long idPerfil
) {
}
