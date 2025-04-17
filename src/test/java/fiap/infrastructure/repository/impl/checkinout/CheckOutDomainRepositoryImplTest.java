package fiap.infrastructure.repository.impl.checkinout;

import fiap.sus.domain.exceptions.CheckOutInException;
import fiap.sus.infrastructure.repository.impl.checkinout.CheckOutDomainRepositoryImpl;
import fiap.sus.infrastructure.repository.jpa.CheckInOutJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static fiap.Helper.getCheckInOutDomain;
import static fiap.Helper.getCheckInOutPersistence;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CheckOutDomainRepositoryImplTest {

    @Mock
    CheckInOutJpaRepository checkInOutJpaRepository;

    @InjectMocks
    CheckOutDomainRepositoryImpl checkOutDomainRepositoryImpl;

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
    void medicoRealizaCheckOut() {
        var checkInOutDomain = getCheckInOutDomain();
        checkInOutDomain.setCheckOut(LocalDateTime.now());

        var checkinResponse = getCheckInOutPersistence();

        when(checkInOutJpaRepository.findByIdMedicoAndIdUnidadeAndCheckIn(ArgumentMatchers.anyString(), anyLong(), any(LocalDate.class)))
                .thenReturn(checkinResponse);
        when(checkInOutJpaRepository.save(checkinResponse)).thenReturn(checkinResponse);

        checkOutDomainRepositoryImpl.medicoRealizaCheckOut(checkInOutDomain);

        verify(checkInOutJpaRepository, times(1)).save(any());
    }

    @Test
    void deveLancarExecaoQuandoMedicoJaRealizouCheckOut(){

        var checkInOutDomain = getCheckInOutDomain();
        var checkinResponse = getCheckInOutPersistence();

        when(checkInOutJpaRepository.findByIdMedicoAndIdUnidadeAndCheckout(ArgumentMatchers.anyString(), anyLong(), any(LocalDate.class)))
                .thenReturn(checkinResponse);

        try {
            checkOutDomainRepositoryImpl.medicoRealizaCheckOut(checkInOutDomain);
        } catch (CheckOutInException e) {
            assertThat(e.getMessage())
                    .contains("O médico já possui check-out feito hoje nessa unidade " + checkinResponse.getIdUnidade());
        }

    }

    @Test
    void deveLancarExecaoQuandoMedicoNaoRealizouCheckIn(){

        var checkInOutDomain = getCheckInOutDomain();
        var checkinResponse = getCheckInOutPersistence();

        when(checkInOutJpaRepository.findByIdMedicoAndIdUnidadeAndCheckIn(ArgumentMatchers.anyString(), anyLong(), any(LocalDate.class)))
                .thenReturn(null);

        try {
            checkOutDomainRepositoryImpl.medicoRealizaCheckOut(checkInOutDomain);
        } catch (CheckOutInException e) {
            assertThat(e.getMessage())
                    .contains("O médico não efetuou o check-in hoje na unidade: " + checkinResponse.getIdUnidade());
        }

    }


}
