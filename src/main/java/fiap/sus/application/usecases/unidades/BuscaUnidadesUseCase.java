package fiap.sus.application.usecases.unidades;

import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.domain.repository.UnidadeDomainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BuscaUnidadesUseCase {

    private final UnidadeDomainRepository repository;

    public BuscaUnidadesUseCase(UnidadeDomainRepository repository) {
        this.repository = repository;
    }

    public Optional<UnidadeDomain> findById(long id) {
        log.info("Buscando unidade por id [{}].", id);
        return repository.findById(id);
    }

    public List<UnidadeDomain> findAll() {
        log.info("Buscando todas as unidades.");
        return repository.buscaTodasUnidades();
    }




}
