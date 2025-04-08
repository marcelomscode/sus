package fiap.sus.infrastructure.repository.impl.checkinout;

import fiap.sus.domain.model.CheckInOutDomain;
import fiap.sus.domain.repository.checkinout.BuscaCheckInOutDomainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuscaCheckInOutRepositoryImpl implements BuscaCheckInOutDomainRepository {


    @Override
    public CheckInOutDomain buscaCheckInOutPorId(long idMedico, long idUnidade) {
        return null;
    }

    @Override
    public List<CheckInOutDomain> buscaCheckInOutPorUnidade(long idUnidade) {
        return List.of();
    }

    @Override
    public List<CheckInOutDomain> buscaCheckInOutPorMedico(long idMedico) {
        return List.of();
    }

    @Override
    public List<CheckInOutDomain> buscaCheckInOutPorData(LocalDateTime dataHora) {
        return List.of();
    }

}
