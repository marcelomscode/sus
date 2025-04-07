package fiap.sus.application.usecases;

import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.domain.repository.UnidadeDomainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BuscaUnidadePorIdUseCase {

    private final UnidadeDomainRepository repository;

    public BuscaUnidadePorIdUseCase(UnidadeDomainRepository repository) {
        this.repository = repository;
    }

    public Optional<UnidadeDomain> findById(long id) {
        log.info("Buscando unidade por id [{}].", id);
        return repository.findById(id);
    }

}
