package souza.luiz.forum.hub.domain.dto.perfil;

import souza.luiz.forum.hub.domain.model.perfil.Perfil;
import souza.luiz.forum.hub.domain.model.perfil.TipoPerfil;

public record DadosDetalhamentoPerfil(
        Long id,
        TipoPerfil nome,
        Boolean ativo
) {
    public DadosDetalhamentoPerfil(Perfil perfil) {
        this(perfil.getId(),perfil.getNome(),perfil.isAtivo());
    }
}
