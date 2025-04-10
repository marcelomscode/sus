package fiap.sus.domain.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class EspecialidadeException extends RuntimeException {

    private final HttpStatus status;
    private final int codigoStatus;

    public EspecialidadeException(String mensagem, HttpStatus status, int codigoStatus) {
        super(mensagem);
        this.status = status;
        this.codigoStatus = codigoStatus;
    }
}