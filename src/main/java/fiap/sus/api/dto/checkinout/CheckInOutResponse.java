package fiap.sus.api.dto.checkinout;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckInOutResponse {

    private Long id;
    private Long idMedico;
    private String UUID;
    private Long idUnidade;
    private String checkIn;
    private String checkOut;
    private String data;

    public CheckInOutResponse(Long id, Long idMedico, Long idUnidade) {
        this.id = id;
        this.idMedico = idMedico;
        this.idUnidade = idUnidade;
    }


    public CheckInOutResponse(Long id, Long idMedico, String UUID, Long idUnidade, String checkIn, String checkOut, String data) {
        this.id = id;
        this.idMedico = idMedico;
        this.UUID = UUID;
        this.idUnidade = idUnidade;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.data = data;
    }

}
