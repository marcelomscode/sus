package fiap.sus.infrastructure.repository.impl.especialidades;

import fiap.sus.domain.exceptions.EspecialidadeException;
import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.domain.repository.especialidade.BuscaEspecialidadesRepository;
import fiap.sus.infrastructure.mappers.EspecialidadePersistenceMapper;
import fiap.sus.infrastructure.repository.jpa.EspecialidadesJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BuscaEspecialidadesRepositoryImpl implements BuscaEspecialidadesRepository {

    private final EspecialidadesJpaRepository repository;

    public BuscaEspecialidadesRepositoryImpl(EspecialidadesJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EspecialidadesDomain> listarTodasEspecialidades() {

        log.info("Listando todas as especialidades");
        var especialidades = repository.findAll();
        if (especialidades.isEmpty()) {
            log.error("Nenhuma especialidade encontrada");
            throw new EspecialidadeException("Nenhuma especialidade encontrada", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }
        return especialidades.stream().map(EspecialidadePersistenceMapper::toDomain).toList();

    }

    @Override
    public EspecialidadesDomain buscarPorId(long id) {
        log.info("Buscando especialidade por id: {}", id);
        var especialidade = repository.findById(id);
        if (especialidade.isEmpty()) {
            log.error("Especialidade não encontrada: {}", id);
            throw new EspecialidadeException("Especialidade não encontrada: "+ id, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }
        return EspecialidadePersistenceMapper.toDomain(especialidade.get());
    }

    @Override
    public EspecialidadesDomain buscarPorNome(String nome) {
        return null;
    }

}
