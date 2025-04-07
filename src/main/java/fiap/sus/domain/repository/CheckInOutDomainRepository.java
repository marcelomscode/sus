package fiap.sus.domain.repository;

import fiap.sus.domain.model.CheckInOutDomain;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckInOutDomainRepository {

    void checkIn(long idMedico, long idUnidade, LocalDateTime checkIn);

    void checkOut(long idMedico, long idUnidade, LocalDateTime checkOut);

    CheckInOutDomain buscaCheckInOutPorId(long idMedico, long idUnidade);

    List<CheckInOutDomain> buscaCheckInOutPorUnidade(long idUnidade);

    List<CheckInOutDomain> buscaCheckInOutPorMedico(long idMedico);

    List<CheckInOutDomain> buscaCheckInOutPorData(LocalDateTime dataHora);


}
