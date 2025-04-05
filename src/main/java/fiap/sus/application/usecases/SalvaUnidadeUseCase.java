package fiap.sus.application.usecases;

import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.domain.repository.UnidadeDomainRepository;
import fiap.sus.infrastructure.persistence.UnidadePersistence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalvaUnidadeUseCase {

    private final UnidadeDomainRepository repository;

    public void save(UnidadeDomain persistence) {
        try {
            repository.save(persistence);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar unidade: " + e.getMessage());
        }
    }


}
