package fiap.sus.infrastructure.repository.impl;

import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.domain.repository.UnidadeDomainRepository;
import fiap.sus.infrastructure.persistence.UnidadePersistence;
import fiap.sus.infrastructure.repository.jpa.UnidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class UnidadeRepositoryImpl implements UnidadeDomainRepository {


    private final UnidadeRepository repository;

    public UnidadeRepositoryImpl(UnidadeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(UnidadeDomain persistence) {
        repository.save(UnidadePersistence
                .builder()
                .nome(persistence.getNome())
                .endereco(persistence.getEndereco())
                .build());
    }
}
