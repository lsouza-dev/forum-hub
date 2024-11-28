package souza.luiz.forum.hub.domain.model.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import souza.luiz.forum.hub.domain.dto.curso.DadosAtualizacaoCurso;
import souza.luiz.forum.hub.domain.dto.curso.DadosCadastroCurso;
import souza.luiz.forum.hub.domain.dto.curso.DadosDetalhamentoCurso;
import souza.luiz.forum.hub.domain.dto.perfil.DadosDetalhamentoPerfil;
import souza.luiz.forum.hub.domain.model.curso.Curso;
import souza.luiz.forum.hub.domain.repository.CursoRepository;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCurso dados, UriComponentsBuilder uriBuilder){
        var curso = new Curso(dados);
        repository.save(curso);

        var uri = uriBuilder.path("/curso/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCurso(curso));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoCurso>> listar(Pageable paginacao){
        var cursos = repository.findByAtivoTrue(paginacao).map(DadosDetalhamentoCurso::new);
        return ResponseEntity.ok().body(cursos);
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var curso = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoCurso(curso));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCurso dados){
        var curso = repository.getReferenceById(dados.id());
        curso.atualizar(dados);
        return ResponseEntity.ok().body(new DadosDetalhamentoCurso(curso));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id){
        var curso = repository.getReferenceById(id);
        curso.desativar();
        return  ResponseEntity.noContent().build();
    }
}
