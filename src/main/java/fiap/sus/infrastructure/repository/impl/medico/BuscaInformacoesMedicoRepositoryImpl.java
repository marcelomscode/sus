package fiap.sus.infrastructure.repository.impl.medico;

import fiap.sus.api.mappers.MedicoMapper;
import fiap.sus.domain.exceptions.MedicoException;
import fiap.sus.domain.model.MedicoDomain;
import fiap.sus.domain.repository.medico.BuscaInformacoesMedicoRepository;
import fiap.sus.infrastructure.external.MedicoFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BuscaInformacoesMedicoRepositoryImpl implements BuscaInformacoesMedicoRepository {

    private final MedicoFeignClient medicoClient;

    public MedicoDomain buscaMedicoByUUID(String uuid) {

        try{
            var medicoResponse = medicoClient.getMedico(uuid);

            return MedicoMapper.toDoMain(medicoResponse);

        }catch (MedicoException e) {
            throw new MedicoException("Erro ao buscar médico por ID: " + uuid, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

    }
}
