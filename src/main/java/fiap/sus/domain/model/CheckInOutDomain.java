package fiap.sus.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CheckInOutDomain {

    private Long id;
    private Long idMedico;
    private Long idUnidade;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

}
