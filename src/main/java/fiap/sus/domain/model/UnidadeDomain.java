package fiap.sus.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnidadeDomain {

    private Integer id;
    private String nome;
    private String endereco;

}
