package souza.luiz.forum.hub.domain.dto.usuario;

import souza.luiz.forum.hub.domain.model.curso.Curso;
import souza.luiz.forum.hub.domain.model.usuario.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String nome,
        String email,
        Long idPerfil,
        boolean ativo
) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(),usuario.getNome(),usuario.getLogin(),usuario.getPerfil().getId(),usuario.isAtivo());
    }

}
