package souza.luiz.forum.hub.domain.model.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import souza.luiz.forum.hub.domain.dto.perfil.DadosAtualizacaoPerfil;
import souza.luiz.forum.hub.domain.dto.perfil.DadosDetalhamentoPerfil;
import souza.luiz.forum.hub.domain.model.perfil.DadosCadastroPerfil;
import souza.luiz.forum.hub.domain.model.perfil.Perfil;
import souza.luiz.forum.hub.domain.repository.PerfilRepository;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPerfil dados, UriComponentsBuilder uriBuilder){
        var perfil = new Perfil(dados);
        repository.save(perfil);

        var uri = uriBuilder.path("/perfil/{id}").buildAndExpand(perfil.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPerfil(perfil));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody DadosAtualizacaoPerfil dados){
        var perfil = repository.getReferenceById(dados.id());
        perfil.atualizar(dados);
        return ResponseEntity.ok().body(new DadosDetalhamentoPerfil(perfil));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoPerfil>> listar(Pageable paginacao){
        var perfis = repository.findByAtivoTrue(paginacao).map(DadosDetalhamentoPerfil::new);
        return ResponseEntity.ok().body(perfis);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var perfil = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoPerfil(perfil));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity desativar(@PathVariable Long id){
        var perfil = repository.getReferenceById(id);
        perfil.desativar();
        return ResponseEntity.noContent().build();
    }
}
