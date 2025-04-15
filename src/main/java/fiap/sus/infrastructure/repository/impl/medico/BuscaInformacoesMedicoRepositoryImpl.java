package fiap.sus.infrastructure.repository.impl.medico;

import fiap.sus.api.dto.MedicoResponse;
import fiap.sus.api.mappers.MedicoMapper;
import fiap.sus.domain.exceptions.MedicoException;
import fiap.sus.domain.model.MedicoDomain;
import fiap.sus.domain.repository.medico.BuscaInformacoesMedicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import java.util.Objects;
import java.util.UUID;

@Repository
public class BuscaInformacoesMedicoRepositoryImpl implements BuscaInformacoesMedicoRepository {

    @Override
    public MedicoDomain findById(long id) {

        var doctor = new MedicoResponse(UUID.randomUUID().toString(),"Dr. João ","Silva", "CRM12345");

        var medico = MedicoMapper.toDoMain(doctor);

        if(Objects.nonNull(medico.getId())) {
            return medico;
        }else{
            throw new MedicoException("Erro ao buscar médico por ID: " + id, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

    }
}
