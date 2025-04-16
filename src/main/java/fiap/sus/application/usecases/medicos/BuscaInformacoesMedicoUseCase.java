package fiap.sus.application.usecases.medicos;

import fiap.sus.domain.model.MedicoDomain;
import fiap.sus.domain.repository.medico.BuscaInformacoesMedicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuscaInformacoesMedicoUseCase {

    private final BuscaInformacoesMedicoRepository repository;

    public MedicoDomain buscaMedicoByUUID(String uuid) {
        return repository.buscaMedicoByUUID(uuid);
    }

}
