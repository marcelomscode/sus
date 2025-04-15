package fiap.sus.api.dto.checkinout;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CheckInOutRequest {

//    private Long id;
    private Long idMedico;
    @Column(nullable = false)
    private String UUID;
    @Column(nullable = false)
    private Long idUnidade;

}
