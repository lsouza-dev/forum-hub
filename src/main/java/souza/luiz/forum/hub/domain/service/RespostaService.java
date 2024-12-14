package souza.luiz.forum.hub.domain.service;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import souza.luiz.forum.hub.domain.dto.resposta.DadosAtualizacaoResposta;
import souza.luiz.forum.hub.domain.dto.resposta.DadosCadastroResposta;
import souza.luiz.forum.hub.domain.model.resposta.Resposta;
import souza.luiz.forum.hub.domain.model.topico.Topico;
import souza.luiz.forum.hub.domain.repository.RespostaRepository;
import souza.luiz.forum.hub.domain.repository.TopicoRepository;
import souza.luiz.forum.hub.domain.repository.UsuarioRepository;

import java.util.List;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Resposta cadastrarResposta(@RequestBody @Valid DadosCadastroResposta dados){

        var topico = topicoRepository.findById(dados.topicoId())
                .orElseThrow(() -> new ValidationException(String.format("Tópico com o ID %d não existe.", dados.topicoId())));

        var autor = usuarioRepository.findById(dados.autorId())
                .orElseThrow(() -> new ValidationException(String.format("Autor com o ID %d não existe.", dados.autorId())));

        var resposta = new Resposta(dados);
        resposta.setTopico(topico);
        resposta.setAutor(autor);

        return resposta;
    }

    public Resposta atualizarResposta(@Valid DadosAtualizacaoResposta dados) {
        var resposta = obterResposta(dados.idResposta());

        resposta.atualizar(dados);
        return  resposta;
    }

    public List<Resposta> buscarRespostasPorTopico(Long idTopico) {
        var topico = topicoRepository.findById(idTopico)
                .orElseThrow(() -> new ValidationException(String.format("Tópico com o ID %d não existe", idTopico)));

        return respostaRepository.obterRespostasAtivasPorTopico(topico);
    }

    public Resposta obterResposta(Long idResposta) {
        return respostaRepository.findById(idResposta)
                .orElseThrow(() -> new ValidationException(String.format("Resposta com o ID %d não existe.", idResposta)));
    }

    public Resposta obterRespostaTopicoRespondido(Topico idTopico) {
        return respostaRepository.obterRespostaRespondidaPorTopico(idTopico);
    }
}
