package fiap.application.usecase.checkinout;

import fiap.sus.application.usecases.checkinout.CheckOutUseCase;
import fiap.sus.domain.repository.checkinout.CheckOutDomainRepository;
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

class CheckOutUseCaseTest {

    @Mock
    CheckOutDomainRepository checkInDomainRepository;

    @InjectMocks
    CheckOutUseCase checkInUseCase;

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
    void medicoDeveRealizarCheckIn() {

        doNothing().when(checkInDomainRepository).medicoRealizaCheckOut(any());

        checkInUseCase.medicoRealizaCheckOut(any());

        verify(checkInDomainRepository, times(1)).medicoRealizaCheckOut(any());
    }


}
