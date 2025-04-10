package fiap.sus.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UnidadeDomain {

    private Long id;
    private String nome;
    private String endereco;
    private Set<EspecialidadesDomain> especialidades;
    private boolean ativo;

}
