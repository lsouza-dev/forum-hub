package souza.luiz.forum.hub.domain.dto.topico;

import souza.luiz.forum.hub.domain.dto.curso.DadosDetalhamentoCurso;
import souza.luiz.forum.hub.domain.dto.usuario.DadosDetalhamentoUsuario;
import souza.luiz.forum.hub.domain.model.topico.Status;
import souza.luiz.forum.hub.domain.model.topico.Topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        Status status,
        DadosDetalhamentoUsuario usuario,
        DadosDetalhamentoCurso Curso
) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatusTopico(),
                new DadosDetalhamentoUsuario(topico.getAutor()),
                new DadosDetalhamentoCurso(topico.getCurso())
        );
    }
}
