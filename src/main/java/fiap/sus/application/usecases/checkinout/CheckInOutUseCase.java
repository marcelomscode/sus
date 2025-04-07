package fiap.sus.application.usecases.checkinout;

import fiap.sus.domain.repository.CheckInOutDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CheckInOutUseCase {

    private final CheckInOutDomainRepository checkInOutRepository;

    //Buscar Informações do médico no MS de cadastro de medicos
    //Salvar infos do medico na unidade ( id, nome, crm, especialidade)
    //Criar tabela de checkinout com id, idMedico, idUnidade, dataHoraCheckIn, dataHoraCheckOut na mesma linha
    //CheckInHora - CheckOutHorah

    public void checkIn(long idMedico, long idUnidade) {
        checkInOutRepository.checkIn(idMedico, idUnidade, LocalDateTime.now());
    }

    public void checkOut(long idMedico, long idUnidade) {
        checkInOutRepository.checkOut(idMedico, idUnidade, LocalDateTime.now());
    }


}
