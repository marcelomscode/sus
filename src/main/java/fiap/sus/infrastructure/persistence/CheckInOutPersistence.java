package fiap.sus.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "checkinout")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckInOutPersistence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idMedico;
    private Long idUnidade;
    private String UUID;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private LocalDateTime data;

}
