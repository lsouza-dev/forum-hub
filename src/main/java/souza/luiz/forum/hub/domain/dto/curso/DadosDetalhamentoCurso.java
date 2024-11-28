package souza.luiz.forum.hub.domain.dto.curso;

import souza.luiz.forum.hub.domain.model.curso.Categoria;
import souza.luiz.forum.hub.domain.model.curso.Curso;

public record DadosDetalhamentoCurso(
        Long id,
        String nome,
        Categoria categoria,
        boolean ativo
) {

    public DadosDetalhamentoCurso(Curso c){
        this(c.getId(),c.getNome(),c.getCategoria(),c.isAtivo());
    }
}
