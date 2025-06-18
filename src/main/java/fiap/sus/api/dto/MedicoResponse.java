package fiap.sus.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MedicoResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String crm;

}
