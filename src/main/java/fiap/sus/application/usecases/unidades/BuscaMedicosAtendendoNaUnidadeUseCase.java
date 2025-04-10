package fiap.sus.application.usecases.unidades;

import fiap.sus.api.dto.unidade.MedicosAtendendoUnidadeResponse;
import fiap.sus.application.usecases.checkinout.BuscaCheckInOutUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuscaMedicosAtendendoNaUnidadeUseCase {

    private final BuscaCheckInOutUseCase buscaCheckInOutUseCase;

    public MedicosAtendendoUnidadeResponse buscaMedicosAtendendoNaUnidade(Long idUnidade, LocalDate data) {
        log.info("Buscando médicos atendendo na unidade com id [{}].", idUnidade);

        return MedicosAtendendoUnidadeResponse
                .builder()
                .idUnidade(idUnidade)
                .medicosAtendendo(buscaCheckInOutUseCase.buscaMedicosComCheckInEmUmaUnidade(idUnidade, data))
                .build();
    }

}
