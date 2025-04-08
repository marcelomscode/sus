package fiap.sus.domain.repository.checkinout;

import fiap.sus.domain.model.CheckInOutDomain;

import java.time.LocalDateTime;
import java.util.List;

public interface BuscaCheckInOutDomainRepository {

    CheckInOutDomain buscaCheckInOutPorId(long idMedico, long idUnidade);

    List<CheckInOutDomain> buscaCheckInOutPorUnidade(long idUnidade);

    List<CheckInOutDomain> buscaCheckInOutPorMedico(long idMedico);

    List<CheckInOutDomain> buscaCheckInOutPorData(LocalDateTime dataHora);


}
