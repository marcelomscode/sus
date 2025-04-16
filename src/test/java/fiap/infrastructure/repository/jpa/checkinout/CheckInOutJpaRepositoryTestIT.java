package fiap.infrastructure.repository.jpa.checkinout;

import fiap.sus.infrastructure.persistence.CheckInOutPersistence;
import fiap.sus.infrastructure.repository.jpa.CheckInOutJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class CheckInOutJpaRepositoryTestIT {

    @Autowired
    private CheckInOutJpaRepository checkInOutJpaRepository;


    @Test
    void deveBuscarMedicoPorIdMedicoEIdUnidade(){

        var checkin = CheckInOutPersistence
                .builder()
                .checkIn(LocalDateTime.now())
                .checkOut(LocalDateTime.now())
                .idMedico(1L)
                .idUnidade(5L)
                .UUID("123e4567-e89b-12d3-a456-426614174005")
                .data(LocalDateTime.now())
                .build();

        checkInOutJpaRepository.save(checkin);

        var checkInOut = checkInOutJpaRepository.findByIdMedicoAndIdUnidade(1, 5);

        assertThat(checkInOut)
                .isNotNull()
                .isSameAs(checkin);
    }

}
