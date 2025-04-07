package fiap.sus.domain.repository;

import fiap.sus.domain.model.UnidadeDomain;

import java.util.List;
import java.util.Optional;

public interface UnidadeDomainRepository {

    void save(UnidadeDomain persistence);

    List<UnidadeDomain> BuscaTodasUnidades();

    Optional<UnidadeDomain> findById(long id);

    void deleteById(Long id);


}
