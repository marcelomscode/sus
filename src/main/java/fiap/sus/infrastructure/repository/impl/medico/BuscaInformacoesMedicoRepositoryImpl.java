package fiap.sus.infrastructure.repository.impl.medico;

import fiap.sus.api.dto.MedicoResponse;
import fiap.sus.api.mappers.MedicoMapper;
import fiap.sus.domain.exceptions.MedicoException;
import fiap.sus.domain.model.MedicoDomain;
import fiap.sus.domain.repository.medico.BuscaInformacoesMedicoRepository;
import fiap.sus.infrastructure.external.MedicoFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import java.util.Objects;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BuscaInformacoesMedicoRepositoryImpl implements BuscaInformacoesMedicoRepository {

    private final MedicoFeignClient medicoClient;

    public MedicoDomain buscaMedicoByUUID(String uuid) {

        var medicoResponse = medicoClient.getMedico(uuid);

        var doctor = new MedicoResponse(UUID.randomUUID().toString(), "Dr. João ", "Silva", "CRM12345");

        var medico = MedicoMapper.toDoMain(doctor);

        if (Objects.nonNull(medico.getId())) {
            return medico;
        } else {
            throw new MedicoException("Erro ao buscar médico por ID: " + uuid, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }
    }
}
