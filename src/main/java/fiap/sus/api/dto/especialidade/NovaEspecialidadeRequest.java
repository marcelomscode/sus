package fiap.sus.api.dto.especialidade;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class NovaEspecialidadeRequest {

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;

}
