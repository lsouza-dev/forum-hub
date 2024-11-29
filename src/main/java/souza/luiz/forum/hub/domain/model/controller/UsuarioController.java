package souza.luiz.forum.hub.domain.model.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import souza.luiz.forum.hub.domain.dto.usuario.DadosAtualizacaoUsuario;
import souza.luiz.forum.hub.domain.dto.usuario.DadosCadastroUsuario;
import souza.luiz.forum.hub.domain.dto.usuario.DadosDetalhamentoUsuario;
import souza.luiz.forum.hub.domain.model.curso.Curso;
import souza.luiz.forum.hub.domain.model.usuario.Usuario;
import souza.luiz.forum.hub.domain.repository.PerfilRepository;
import souza.luiz.forum.hub.domain.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(dados);
        usuario.setPerfil(perfilRepository.getReferenceById(dados.idPerfil()));
        usuarioRepository.save(usuario);

        var uri = uriBuilder.path("/curso/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listar(Pageable paginacao){
        var usuarios = usuarioRepository.findByAtivoTrue(paginacao).map(DadosDetalhamentoUsuario::new);
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUsuario dados){
        var usuario = usuarioRepository.getReferenceById(dados.id());
        usuario.atualizar(dados);
        if(dados.idPerfil() != null){
            usuario.setPerfil(perfilRepository.getReferenceById(dados.idPerfil()));
        }
        return  ResponseEntity.ok().body(new DadosDetalhamentoUsuario(usuario));
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoUsuario(usuario));
    }
}
