package fiap.sus.application.usecases;

import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.domain.repository.UnidadeDomainRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SalvaUnidadeUseCase {

    private final UnidadeDomainRepository repository;

    private static final String UNIDADE_SALVA = "Unidade salva com sucesso: {}";
    private static final String ERRO_SALVAR_UNIDADE = "Erro ao salvar unidade: {}";


    public void save(UnidadeDomain persistence) {
        try {
            if (persistence.getNome() == null || persistence.getNome().isEmpty()) {
                throw new IllegalArgumentException("Nome da unidade não pode ser nulo ou vazio");
            }
            repository.save(persistence);
            log.info(UNIDADE_SALVA, persistence);
        } catch (Exception e) {
            log.warn(ERRO_SALVAR_UNIDADE, e.getMessage());
            throw new RuntimeException(ERRO_SALVAR_UNIDADE + e.getMessage());
        }
    }

}
