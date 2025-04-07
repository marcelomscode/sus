package fiap.sus.api.controllers;

import fiap.sus.api.dto.UnidadeDTO;
import fiap.sus.application.usecases.BuscaUnidadePorIdUseCase;
import fiap.sus.application.usecases.DeletaUnidadeUseCase;
import fiap.sus.application.usecases.SalvaUnidadeUseCase;
import fiap.sus.domain.model.UnidadeDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/unidades")
@RequiredArgsConstructor
public class UnidadeController {

    private final SalvaUnidadeUseCase salvaUnidadeUseCase;
    private final BuscaUnidadePorIdUseCase buscaUnidadePorIdUseCase;
    private final DeletaUnidadeUseCase deletaUnidadeUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvaUnidade(@RequestBody UnidadeDTO unidadeDTO) {

        log.info("Salvando unidade: {}", unidadeDTO.getNome());
        var unidade = new UnidadeDomain(unidadeDTO.getId(), unidadeDTO.getNome(), unidadeDTO.getEndereco());
        log.info("Unidade Salva com sucesso: {}", unidade.getNome());
        salvaUnidadeUseCase.save(unidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaUnidade(@PathVariable Long id) {
        log.info("Deletando unidade com id: [{}]", id);

        buscaUnidadePorIdUseCase.findById(id).ifPresentOrElse(
                unidade -> deletaUnidadeUseCase.deleteById(id),
                () -> log.warn("Unidade não encontrada com id: [{}]", id)
        );
    }



    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDomain> buscaUnidadePorId(@PathVariable Long id) {

        return buscaUnidadePorIdUseCase.findById(id).map(
                        ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UnidadeDomain> buscaListaUnidades() {
        return List.of(new UnidadeDomain(1L, "Unidade 1", "Rua A"),
                new UnidadeDomain(2L, "Unidade 2", "Rua B"));
    }

    //Pegar lista de medicos atendendo por unidade
    @GetMapping("medicos-por-unidade/{IdUnidade}")
    public List<UnidadeDomain> buscaMedicosPorUnidade(@PathVariable Long IdUnidade) {
        return List.of(new UnidadeDomain(1L, "Unidade 1", "Rua A"),
                new UnidadeDomain(2L, "Unidade 2", "Rua B"));
    }

}
