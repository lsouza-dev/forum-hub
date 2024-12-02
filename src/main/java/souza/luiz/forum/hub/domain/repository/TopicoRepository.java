package souza.luiz.forum.hub.domain.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import souza.luiz.forum.hub.domain.model.curso.Categoria;
import souza.luiz.forum.hub.domain.model.topico.Topico;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
    Page<Topico> findAllByAtivoTrue(Pageable paginacao);

    boolean existsByTituloOrMensagem(@NotBlank String titulo, @NotBlank String mensagem);

    @Query("""
        SELECT t FROM Topico t
        JOIN  t.curso c
        WHERE c.nome ILIKE %:nomeCurso%
    """)
    Page<Topico> buscarTopicosPorNomeDoCurso(Pageable paginacao, String nomeCurso);

    @Query("""
            SELECT t FROM Topico t
            JOIN t.curso c
            WHERE c.categoria = :categoria
            """)
    Page<Topico> buscarTopicosPorCategoria(Pageable paginacao, Categoria categoria);

    @Query("""
            SELECT t FROM Topico t
            WHERE YEAR(t.dataCriacao) = :ano
            """)
    Page<Topico> buscarTopicosPorAnoDeCriacao(Pageable paginacao, int ano);
}
