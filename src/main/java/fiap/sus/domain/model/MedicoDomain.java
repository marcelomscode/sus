package fiap.sus.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedicoDomain {

    private Long id;
    private String UUID;
    private String nome;
    private String crm;

}
