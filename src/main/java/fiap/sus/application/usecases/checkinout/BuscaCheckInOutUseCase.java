package fiap.sus.application.usecases.checkinout;

import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.domain.repository.checkinout.BuscaCheckInOutDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscaCheckInOutUseCase {

    private final BuscaCheckInOutDomainRepository checkInOutDomainRepository;

    //Buscar Informações do médico no MS de cadastro de medicos

    public List<CheckInOutDomain> buscaCheckInOutPorId(long idMedico, long idUnidade) {
        return List.of(checkInOutDomainRepository.buscaCheckInOutPorId(idMedico, idUnidade));
    }

    public List<CheckInOutDomain> buscaCheckInOutPorUnidade(long idUnidade) {
        return checkInOutDomainRepository.buscaCheckInOutPorUnidade(idUnidade);
    }

    public List<CheckInOutDomain> buscaCheckInOutPorMedico(long idMedico) {
        return checkInOutDomainRepository.buscaCheckInOutPorMedico(idMedico);
    }

    public List<CheckInOutDomain> buscaCheckInOutPorData(LocalDateTime dataHora) {
        return checkInOutDomainRepository.buscaCheckInOutPorData(dataHora);
    }

}
