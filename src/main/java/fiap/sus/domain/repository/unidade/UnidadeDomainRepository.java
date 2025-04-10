package fiap.sus.domain.repository.unidade;

import fiap.sus.domain.model.UnidadeDomain;

import java.util.List;
import java.util.Optional;

public interface UnidadeDomainRepository {

    void save(UnidadeDomain persistence);

    List<UnidadeDomain> buscaTodasUnidades();

    Optional<UnidadeDomain> findById(long id);

    void deleteById(Long id);

    UnidadeDomain update(UnidadeDomain unidadeDomain);

}
