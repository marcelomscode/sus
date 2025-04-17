package fiap.sus.domain.repository.checkinout;

import fiap.sus.domain.model.CheckInOutDomain;

import java.time.LocalDate;
import java.util.List;

public interface BuscaCheckInOutDomainRepository {

    CheckInOutDomain buscaCheckInOutPorMedicoPorUnidade(long idMedico, long idUnidade);

    List<CheckInOutDomain> buscaCheckInOutPorUnidade(long idUnidade);

    List<CheckInOutDomain> buscaCheckInOutPorMedicoUUID(long idMedico);

    List<CheckInOutDomain> buscaCheckInOutPorMedicoUUID(String idMedico);

    List<CheckInOutDomain> buscaCheckInOutPorMedicoEPorUnidadeEPorDataCheckIn(String uuid, long idUnidade, LocalDate data);

    List<CheckInOutDomain> buscaCheckInOutPorUnidadeEData(long idUnidade, LocalDate dataHora);

    List<CheckInOutDomain> buscaMedicosComCheckInEmUmaUnidade(long idUnidade, LocalDate data);
}
