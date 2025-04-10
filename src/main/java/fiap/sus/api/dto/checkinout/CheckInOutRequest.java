package fiap.sus.api.dto.checkinout;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckInOutRequest {

    private Long id;
    private Long idMedico;
    private Long idUnidade;

    public CheckInOutRequest(Long id, Long idMedico, Long idUnidade) {
        this.id = id;
        this.idMedico = idMedico;
        this.idUnidade = idUnidade;
    }

}
