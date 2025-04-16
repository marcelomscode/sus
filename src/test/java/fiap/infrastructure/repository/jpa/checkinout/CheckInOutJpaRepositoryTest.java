package fiap.infrastructure.repository.jpa.checkinout;

import fiap.sus.infrastructure.persistence.CheckInOutPersistence;
import fiap.sus.infrastructure.repository.jpa.CheckInOutJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CheckInOutJpaRepositoryTest {

    @Mock
    private CheckInOutJpaRepository checkInOutJpaRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }


    @Nested
    class buscaCheckinOut {

        @Test
        void deveBuscarMedicoPorIdMedicoEIdUnidade() {

            var checkin = CheckInOutPersistence
                    .builder()
                    .checkIn(LocalDateTime.now())
                    .checkOut(LocalDateTime.now())
                    .idMedico(1L)
                    .idUnidade(5L)
                    .UUID("123e4567-e89b-12d3-a456-426614174005")
                    .data(LocalDateTime.now())
                    .build();

            when(checkInOutJpaRepository.findByIdMedicoAndIdUnidade(1L, 5L)).thenReturn(checkin);

           var checkInOut =  checkInOutJpaRepository.findByIdMedicoAndIdUnidade(1, 5);

            assertThat(checkInOut)
                    .isNotNull()
                    .isSameAs(checkin);

            verify(checkInOutJpaRepository, times(1)).findByIdMedicoAndIdUnidade(1, 5);
        }
    }


}
