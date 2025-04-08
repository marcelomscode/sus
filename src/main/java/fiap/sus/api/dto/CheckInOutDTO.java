package fiap.sus.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckInOutDTO {

    private Long id;
    private Long idMedico;
    private Long idUnidade;

    public CheckInOutDTO(Long id, Long idMedico, Long idUnidade) {
        this.id = id;
        this.idMedico = idMedico;
        this.idUnidade = idUnidade;
    }

}
