package fiap.application.usecase.checkinout;

import fiap.sus.application.usecases.checkinout.CheckInUseCase;
import fiap.sus.domain.repository.checkinout.CheckInDomainRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CheckInUseCaseTest {

    @Mock
    CheckInDomainRepository checkInDomainRepository;

    @InjectMocks
    CheckInUseCase checkInUseCase;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void medicoDeveRealizarCheckIn(){

        doNothing().when(checkInDomainRepository).medicoRealizarCheckIn(any());

        checkInUseCase.medicoRealizarCheckIn(any());

        verify(checkInDomainRepository, times(1)).medicoRealizarCheckIn(any());
    }

}
