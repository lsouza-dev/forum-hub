package souza.luiz.forum.hub.domain.dto.usuario.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        String login,
        String senha
) {
}
