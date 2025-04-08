package fiap.sus.domain.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CheckOutInException extends RuntimeException {

    private final HttpStatus status;
    private final int codigo;

    public CheckOutInException(String mensagem, HttpStatus status, int codigoStatus) {
        super(mensagem);
        this.status = status;
        this.codigo = codigoStatus;
    }

}
