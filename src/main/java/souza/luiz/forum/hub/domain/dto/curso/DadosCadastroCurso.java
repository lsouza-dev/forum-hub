package souza.luiz.forum.hub.domain.dto.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import souza.luiz.forum.hub.domain.model.curso.Categoria;

public record DadosCadastroCurso(
        @NotBlank String nome,
        @NotNull Categoria categoria
        ) {
}
