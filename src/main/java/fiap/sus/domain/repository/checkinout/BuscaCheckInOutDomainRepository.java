package fiap.sus.domain.repository.checkinout;

import fiap.sus.domain.model.CheckInOutDomain;

import java.time.LocalDate;
import java.util.List;

public interface BuscaCheckInOutDomainRepository {

    CheckInOutDomain buscaCheckInOutPorMedicoPorUnidade(long idMedico, long idUnidade);

    List<CheckInOutDomain> buscaCheckInOutPorUnidade(long idUnidade);

    List<CheckInOutDomain> buscaCheckInOutPorMedico(long idMedico);

    List<CheckInOutDomain> buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(long idMedico, long idUnidade, LocalDate data);

    List<CheckInOutDomain> buscaCheckInOutPorUnidadeEData(long idUnidade, LocalDate dataHora);

}
