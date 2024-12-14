package souza.luiz.forum.hub.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import souza.luiz.forum.hub.domain.model.resposta.Resposta;
import souza.luiz.forum.hub.domain.model.topico.Topico;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta,Long> {

    @Query("""
            SELECT r FROM Resposta r
            JOIN r.topico t
            WHERE t = :topico
            AND r.ativo = true
            """)
    List<Resposta> obterRespostasAtivasPorTopico(Topico topico);

    @Query("""
            SELECT r FROM Resposta r
            JOIN r.topico t
            WHERE t = :topico
            AND r.solucao = true
            """)
    Resposta obterRespostaRespondidaPorTopico(Topico topico);
}
