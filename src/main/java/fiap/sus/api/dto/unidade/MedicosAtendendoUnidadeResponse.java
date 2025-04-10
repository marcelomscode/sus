package fiap.sus.api.dto.unidade;


import fiap.sus.domain.model.CheckInOutDomain;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MedicosAtendendoUnidadeResponse {

    Long idUnidade;
    List<CheckInOutDomain> medicosAtendendo;

}
