package fiap.sus.application.usecases.especialidades;

import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.domain.repository.especialidade.EspecialidadeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalvaEspecialidadeUseCase {

    private final EspecialidadeRepository repository;

    public void save(EspecialidadesDomain especialidadeDomain) {
        log.info("Salvando especialidade: {}", especialidadeDomain);
        repository.save(especialidadeDomain);
    }

}
