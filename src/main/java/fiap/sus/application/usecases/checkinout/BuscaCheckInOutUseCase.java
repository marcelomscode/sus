package fiap.sus.application.usecases.checkinout;

import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.domain.repository.checkinout.BuscaCheckInOutDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscaCheckInOutUseCase {

    private final BuscaCheckInOutDomainRepository checkInOutDomainRepository;

    //Buscar Informações do médico no MS de cadastro de medicos

    public List<CheckInOutDomain> buscaCheckInOutPorMedicoPorUnidade(long idMedico, long idUnidade) {
        return List.of(checkInOutDomainRepository.buscaCheckInOutPorMedicoPorUnidade(idMedico, idUnidade));
    }

    public List<CheckInOutDomain> buscaCheckInOutPorUnidade(long idUnidade) {
        return checkInOutDomainRepository.buscaCheckInOutPorUnidade(idUnidade);
    }

    public List<CheckInOutDomain> buscaCheckInOutPorMedico(long idMedico) {
        return checkInOutDomainRepository.buscaCheckInOutPorMedico(idMedico);
    }

    public List<CheckInOutDomain> buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(long idMedico, long idUnidade, LocalDate data){
        return checkInOutDomainRepository.buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(idMedico, idUnidade, data);
    }

    public List<CheckInOutDomain> buscaCheckInOutPorUnidadeEData(long idUnidade, LocalDate dataHora) {
        return checkInOutDomainRepository.buscaCheckInOutPorUnidadeEData(idUnidade, dataHora);
    }

}
