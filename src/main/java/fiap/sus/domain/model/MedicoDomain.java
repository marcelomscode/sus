package fiap.sus.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MedicoDomain {

    private Long id;
    private String UUID;
    private String nome;
    private String crm;

}
