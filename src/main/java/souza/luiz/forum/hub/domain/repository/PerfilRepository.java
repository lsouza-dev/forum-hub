package souza.luiz.forum.hub.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import souza.luiz.forum.hub.domain.model.perfil.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil,Long> {
    Page<Perfil> findByAtivoTrue(Pageable paginacao);
}
