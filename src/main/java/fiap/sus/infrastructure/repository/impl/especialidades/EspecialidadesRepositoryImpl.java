package fiap.sus.infrastructure.repository.impl.especialidades;

import fiap.sus.domain.exceptions.EspecialidadeException;
import fiap.sus.domain.model.EspecialidadesDomain;
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

    public EspecialidadesRepositoryImpl(EspecialidadesJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public EspecialidadesDomain save(EspecialidadesDomain especialidade) {

        try {
            log.info("Salvando especialidade " + especialidade);
            var especialidadePersistence = repository.save(EspecialidadePersistenceMapper.toPersistence(especialidade));

            return EspecialidadePersistenceMapper.toDomain(especialidadePersistence);
        } catch (EspecialidadeException e) {
            log.error("Error ao salvar especialidade: {}", e.getMessage());
            throw new EspecialidadeException("Erro ao salvar especialidade.", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        }

    }

    @Override
    public EspecialidadesDomain atualizar(EspecialidadesDomain especialidade) {

        try {

            var especialidadeAntiga = repository.findById(especialidade.getId()).orElseThrow(() -> {
                log.error("Especialidade não encontrada com id: {}", especialidade.getId());
                return new EspecialidadeException("Especialidade não encontrada.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
            });

            especialidadeAntiga.setNome(especialidade.getNome());
            especialidadeAntiga.setDescricao(especialidade.getDescricao());

            log.info("Atualizando especialidade " + especialidade);
            var especialidadePersistence = repository.save(especialidadeAntiga);

            return EspecialidadePersistenceMapper.toDomain(especialidadePersistence);
        } catch (EspecialidadeException e) {
            log.error("Error ao atualizar especialidade: {}", e.getMessage());
            throw new EspecialidadeException("Erro ao atualizar especialidade: " + e.getMessage(), HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
        }
    }


}
