package souza.luiz.forum.hub.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import souza.luiz.forum.hub.domain.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Page<Usuario> findByAtivoTrue(Pageable paginacao);
}
