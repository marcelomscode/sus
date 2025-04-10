package fiap.sus.infrastructure.repository.impl.especialidades;

import fiap.sus.domain.exceptions.EspecialidadeException;
import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.domain.repository.especialidade.BuscaEspecialidadesRepository;
import fiap.sus.domain.repository.especialidade.EspecialidadeRepository;
import fiap.sus.infrastructure.mappers.EspecialidadePersistenceMapper;
import fiap.sus.infrastructure.repository.jpa.EspecialidadesJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EspecialidadesRepositoryImpl implements EspecialidadeRepository {

    private final EspecialidadesJpaRepository repository;
    private final BuscaEspecialidadesRepository buscaEspecialidadeDomainRepository;

    public EspecialidadesRepositoryImpl(EspecialidadesJpaRepository repository, BuscaEspecialidadesRepository buscaEspecialidadeDomainRepository) {
        this.repository = repository;
        this.buscaEspecialidadeDomainRepository = buscaEspecialidadeDomainRepository;
    }

    @Override
    public EspecialidadesDomain save(EspecialidadesDomain especialidade) {

        try {
            log.info("Salvando especialidade " + especialidade);
            var especialidadePersistence = repository.save(EspecialidadePersistenceMapper.toPersistence(especialidade));

            return EspecialidadePersistenceMapper.toDomain(especialidadePersistence);
        } catch (EspecialidadeException e) {
            log.error("Error ao salvar especialidade: {}", e.getMessage());
            throw new EspecialidadeException("Erro ao salvar especialidade: " + e, HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        }

    }

    @Override
    public EspecialidadesDomain atualizar(EspecialidadesDomain especialidade) {

        try {
            buscaEspecialidadeDomainRepository.buscarPorId(especialidade.getId());

            log.info("Atualizando especialidade " + especialidade);
            var especialidadePersistence = repository.save(EspecialidadePersistenceMapper.toPersistence(especialidade));

            return EspecialidadePersistenceMapper.toDomain(especialidadePersistence);
        } catch (EspecialidadeException e) {
            log.error("Error ao atualizar especialidade: {}", e.getMessage());
            throw new EspecialidadeException("Erro ao atualizar especialidade: " + e, HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        }
    }


}
