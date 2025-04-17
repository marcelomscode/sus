package fiap.sus.application.usecases.especialidades;

import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.domain.repository.especialidade.BuscaEspecialidadesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuscaEspecialidadesUseCase {

    private final BuscaEspecialidadesRepository buscaEspecialidadeDomainRepository;

    public List<EspecialidadesDomain> listarTodasEspecialidades() {
        log.info("Listando todas as especialidades");
        return buscaEspecialidadeDomainRepository.buscarTodasEspecialidades();
    }
}
