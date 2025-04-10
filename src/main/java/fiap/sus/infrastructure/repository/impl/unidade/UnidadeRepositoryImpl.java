package fiap.sus.infrastructure.repository.impl.unidade;

import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.domain.repository.unidade.UnidadeDomainRepository;
import fiap.sus.infrastructure.mappers.EspecialidadePersistenceMapper;
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
    private final UnidadePersistenceMapper mapper;
    private final EspecialidadePersistenceMapper especialidadeMapper;

    @Override
    public void save(UnidadeDomain persistence) {

        repository.save(UnidadePersistence
                .builder()
                .nome(persistence.getNome())
                .endereco(persistence.getEndereco())
                .especialidades(especialidadeMapper.toPersistence(persistence.getEspecialidades()))
                .ativo(true)
                .build());
    }

    @Override
    public List<UnidadeDomain> buscaTodasUnidades() {

        List<UnidadePersistence> unidades = repository.findAll();
        log.info("Listando todas as [" + unidades.size() + "] unidades.");
        return unidades.stream().map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<UnidadeDomain> findById(long id) {

        var unidade = repository.findById(id).map(mapper::toDomain);
        log.info("Unidade com id [{}] encontrada.", id);
        return unidade;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
        log.info("Unidade [{}] deletada com sucesso.", id);
    }

    @Override
    public UnidadeDomain update(UnidadeDomain unidadeDomain) {

        var unidade = repository.findById(unidadeDomain.getId())
                .orElseThrow(() -> new IllegalArgumentException("Unidade não encontrada com id: " + unidadeDomain.getId()));

        unidade.setId(unidadeDomain.getId());
        unidade.setNome(unidadeDomain.getNome());
        unidade.setEndereco(unidadeDomain.getEndereco());
        unidade.setAtivo(unidadeDomain.isAtivo());
        var unidadeAtualizada = repository.save(unidade);
        log.info("Unidade atualizada com sucesso.");

        return mapper.toDomain(unidadeAtualizada);
    }

}
