package souza.luiz.forum.hub.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import souza.luiz.forum.hub.domain.model.curso.Curso;

public interface CursoRepository extends JpaRepository<Curso,Long> {
    Page<Curso> findByAtivoTrue(Pageable paginacao);
}
