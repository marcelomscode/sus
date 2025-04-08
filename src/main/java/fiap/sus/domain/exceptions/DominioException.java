package fiap.sus.domain.exceptions;

import org.springframework.http.HttpStatus;
import java.util.Map;

public record DominioException(HttpStatus status, int codigo, Map<String, String> erros) {}
