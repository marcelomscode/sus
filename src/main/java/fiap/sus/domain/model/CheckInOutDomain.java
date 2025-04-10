package fiap.sus.domain.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CheckInOutDomain {

    private Long id;
    @Column(nullable = false)
    private Long idMedico;
    @Column(nullable = false)
    private Long idUnidade;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private LocalDateTime data;

}
