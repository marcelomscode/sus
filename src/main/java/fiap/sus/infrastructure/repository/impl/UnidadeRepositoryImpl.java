package fiap.sus.infrastructure.repository.impl;

import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.domain.repository.UnidadeDomainRepository;
import fiap.sus.infrastructure.mappers.UnidadePersistenceMapper;
import fiap.sus.infrastructure.persistence.UnidadePersistence;
import fiap.sus.infrastructure.repository.jpa.UnidadeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UnidadeRepositoryImpl implements UnidadeDomainRepository {

    private final UnidadeJpaRepository repository;

    @Override
    public void save(UnidadeDomain persistence) {
        repository.save(UnidadePersistence
                .builder()
                .nome(persistence.getNome())
                .endereco(persistence.getEndereco())
                .build());
    }

    @Override
    public List<UnidadeDomain> BuscaTodasUnidades() {

        List<UnidadePersistence> unidades = repository.findAll();

        return unidades.stream().map(UnidadePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<UnidadeDomain> findById(long id) {

        var unidade = repository.findById(id).map(UnidadePersistenceMapper::toDomain);
        log.info("Unidade com id [{}] encontrada.", id);
        return unidade;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
        log.info("Unidade [{}] deletada com sucesso.", id);
    }

}
