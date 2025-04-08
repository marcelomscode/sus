package fiap.sus.infrastructure.exceptionhandler;

import fiap.sus.domain.exceptions.CheckOutInException;
import fiap.sus.domain.exceptions.DominioException;
import fiap.sus.domain.exceptions.UnidadeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Map<String, String> errors = new HashMap<>();

    @ExceptionHandler(CheckOutInException.class)
    public ResponseEntity<DominioException> checkInOutException(CheckOutInException ex) {
        this.limparErrors();
        errors.put("erro", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(new DominioException(ex.getStatus(), ex.getCodigo(), errors));
    }

    @ExceptionHandler(UnidadeException.class)
    public ResponseEntity<DominioException> unidadeException(UnidadeException ex) {
        this.limparErrors();
        errors.put("erro", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(new DominioException(ex.getStatus(), ex.getCodigoStatus(), errors));
    }

    private void limparErrors() {
        errors.clear();
    }

}
