package fiap.sus.application.usecases.checkinout;

import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.domain.repository.CheckInOutDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscaCheckInOutUseCase {

    private final CheckInOutDomainRepository checkInOutRepository;

    //Buscar Informações do médico no MS de cadastro de medicos
    //Salvar infos do medico na unidade ( id, nome, crm, especialidade)
    //Criar tabela de checkinout com id, idMedico, idUnidade, dataHoraCheckIn, dataHoraCheckOut na mesma linha
    //CheckInHora - CheckOutHorah

    public List<CheckInOutDomain> buscaCheckInOutPorId(long idMedico, long idUnidade) {
        return List.of(checkInOutRepository.buscaCheckInOutPorId(idMedico, idUnidade));
    }

    public List<CheckInOutDomain> buscaCheckInOutPorUnidade(long idUnidade) {
        return checkInOutRepository.buscaCheckInOutPorUnidade(idUnidade);
    }

    public List<CheckInOutDomain> buscaCheckInOutPorMedico(long idMedico) {
        return checkInOutRepository.buscaCheckInOutPorMedico(idMedico);
    }

    public List<CheckInOutDomain> buscaCheckInOutPorData(LocalDateTime dataHora) {
        return checkInOutRepository.buscaCheckInOutPorData(dataHora);
    }

}
