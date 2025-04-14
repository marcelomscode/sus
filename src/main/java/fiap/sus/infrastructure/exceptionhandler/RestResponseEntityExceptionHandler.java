package fiap.sus.infrastructure.exceptionhandler;

import fiap.sus.domain.exceptions.CheckOutInException;
import fiap.sus.domain.exceptions.DominioException;
import fiap.sus.domain.exceptions.EspecialidadeException;
import fiap.sus.domain.exceptions.MedicoException;
import fiap.sus.domain.exceptions.UnidadeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DominioException> dataIntegrityViolationException(DataIntegrityViolationException ex) {
        this.limparErrors();
        errors.put("erro", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new DominioException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), errors));
    }

    @ExceptionHandler(UnidadeException.class)
    public ResponseEntity<DominioException> unidadeException(UnidadeException ex) {
        this.limparErrors();
        errors.put("erro", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(new DominioException(ex.getStatus(), ex.getCodigoStatus(), errors));
    }

    @ExceptionHandler(EspecialidadeException.class)
    public ResponseEntity<DominioException> especialidadeException(EspecialidadeException ex) {
        this.limparErrors();
        errors.put("erro", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(new DominioException(ex.getStatus(), ex.getCodigoStatus(), errors));
    }

    @ExceptionHandler(MedicoException.class)
    public ResponseEntity<DominioException> especialidadeException(MedicoException ex) {
        this.limparErrors();
        errors.put("erro", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(new DominioException(ex.getStatus(), ex.getCodigoStatus(), errors));
    }

    private void limparErrors() {
        errors.clear();
    }

}
