package fiap.sus.infrastructure.repository.impl.medico;

import fiap.sus.domain.exceptions.MedicoException;
import fiap.sus.domain.model.MedicoDomain;
import fiap.sus.domain.repository.medico.BuscaInformacoesMedicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import java.util.Objects;

@Repository
public class BuscaInformacoesMedicoRepositoryImpl implements BuscaInformacoesMedicoRepository {

    @Override
    public MedicoDomain findById(long id) {

        var medico = new MedicoDomain(1L, "Dr. João Silva", "Cardiologia", "CRM12345");

        if(Objects.nonNull(medico.getId())) {
            return medico;
        }else{
            throw new MedicoException("Erro ao buscar médico por ID: " + id, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
        }

    }
}
