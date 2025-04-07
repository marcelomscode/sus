package fiap.sus.application.usecases.unidades;

import fiap.sus.domain.repository.UnidadeDomainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeletaUnidadeUseCase {

    private final UnidadeDomainRepository repository;

    public void deleteById(Long id) {
        log.info("Deletando unidade por id [{}]", id);
        repository.deleteById(id);
    }

}
