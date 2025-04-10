package fiap.sus.application.usecases.especialidades;

import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.domain.repository.especialidade.EspecialidadeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AtualizaEspecialidadeUseCase {

    private final EspecialidadeRepository repository;

    public EspecialidadesDomain atualizar(EspecialidadesDomain especialidadeDomain) {
        log.info("Atualizando especialidade: {}", especialidadeDomain);
        return repository.atualizar(especialidadeDomain);
    }

}
