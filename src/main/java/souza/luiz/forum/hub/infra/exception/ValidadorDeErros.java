package souza.luiz.forum.hub.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@RestControllerAdvice
public class ValidadorDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return  ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public  ResponseEntity tratarErro404(EntityNotFoundException ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity tratarErro500(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
    }

    private record DadosErroValidacao(String campo,String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(),erro.getDefaultMessage());
        }
    }
}