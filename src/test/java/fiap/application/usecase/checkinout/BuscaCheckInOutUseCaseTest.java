package fiap.application.usecase.checkinout;

import fiap.sus.application.usecases.checkinout.BuscaCheckInOutUseCase;
import fiap.sus.domain.repository.checkinout.BuscaCheckInOutDomainRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static fiap.Helper.getCheckInOutDomain;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BuscaCheckInOutUseCaseTest {

    @Mock
    BuscaCheckInOutDomainRepository checkInDomainRepository;

    @InjectMocks
    BuscaCheckInOutUseCase checkInUseCase;

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
    void deveBuscarCheckInOutPorMedicoPorUnidade(){
        var medico = getMedico();
        var unidade = getUnidade();

        var checkinDomain = getCheckInOutDomain();

        when(checkInDomainRepository.buscaCheckInOutPorMedicoPorUnidade(medico, unidade)).thenReturn(checkinDomain);

        checkInUseCase.buscaCheckInOutPorMedicoPorUnidade(medico, unidade);

        verify(checkInDomainRepository).buscaCheckInOutPorMedicoPorUnidade(medico, unidade);
    }

    @Test
    void deveBuscaCheckInOutPorUnidade(){
        var unidade = getMedico();

        var checkinDomain = getCheckInOutDomain();

        when(checkInDomainRepository.buscaCheckInOutPorUnidade(unidade)).thenReturn(List.of(checkinDomain));

        checkInUseCase.buscaCheckInOutPorUnidade(unidade);

        verify(checkInDomainRepository).buscaCheckInOutPorUnidade(unidade);
    }

    @Test
    void deveBuscarCheckInOutPorMedico(){
        var medico = getMedico();

        var checkinDomain = getCheckInOutDomain();

        when(checkInDomainRepository.buscaCheckInOutPorMedicoUUID(medico)).thenReturn(List.of(checkinDomain));

        checkInUseCase.buscaCheckInOutPorMedico(medico);

        verify(checkInDomainRepository).buscaCheckInOutPorMedicoUUID(medico);
    }

    @Test
    void deveBuscarCheckInOutPorMedicoUUID(){

        var checkinDomain = getCheckInOutDomain();

        when(checkInDomainRepository.buscaCheckInOutPorMedicoUUID(anyString())).thenReturn(List.of(checkinDomain));

        checkInUseCase.buscaCheckInOutPorMedico(anyString());

        verify(checkInDomainRepository).buscaCheckInOutPorMedicoUUID(anyString());

    }

    @Test
    void deveBuscarCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(){
        var uuid = "c2251775-7d16-4eaf-8334-f8caed547389";
        var checkinDomain = getCheckInOutDomain();
        var data = LocalDate.now();

        when(checkInDomainRepository.buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn
                (uuid, getUnidade(), data)).thenReturn(List.of(checkinDomain));

        checkInUseCase.buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(uuid, getUnidade(), data);

        verify(checkInDomainRepository).buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(uuid, getUnidade(), data);

    }

    @Test
    void deveBuscarCheckInOutPorUnidadeEData(){

        var checkinDomain = getCheckInOutDomain();
        var data = LocalDate.now();

        when(checkInDomainRepository.buscaCheckInOutPorUnidadeEData(getUnidade(), data)).thenReturn(List.of(checkinDomain));

        checkInUseCase.buscaCheckInOutPorUnidadeEData(getUnidade(), data);

        verify(checkInDomainRepository).buscaCheckInOutPorUnidadeEData(getUnidade(), data);
    }

    @Test
    void deveBuscarMedicosComCheckInEmUmaUnidade(){

        var checkinDomain = getCheckInOutDomain();
        var data = LocalDate.now();

        when(checkInDomainRepository.buscaMedicosComCheckInEmUmaUnidade(getUnidade(), data)).thenReturn(List.of(checkinDomain));

        checkInUseCase.buscaMedicosComCheckInEmUmaUnidade(getUnidade(), data);

        verify(checkInDomainRepository).buscaMedicosComCheckInEmUmaUnidade(getUnidade(), data);

    }

    private static long getMedico() {
        return 1L;
    }
    private static long getUnidade() {
        return 1L;
    }


}
