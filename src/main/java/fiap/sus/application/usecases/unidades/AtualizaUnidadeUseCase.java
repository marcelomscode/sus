package fiap.sus.application.usecases.unidades;

import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.domain.repository.unidade.UnidadeDomainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AtualizaUnidadeUseCase {

    private final UnidadeDomainRepository repository;

    public UnidadeDomain update(Long id, String nome, String endereco, boolean ativo) {
        log.info("Buscando unidade: [{}] para atualização", id);
        var unidade = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unidade não encontrada"));
        log.info("Unidade encontrada: [{}]", unidade.getNome());
        unidade.setNome(nome);
        unidade.setEndereco(endereco);
        unidade.setAtivo(ativo);
        repository.update(unidade);

        return unidade;
    }

}
