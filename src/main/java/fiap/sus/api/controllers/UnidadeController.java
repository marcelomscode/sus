package fiap.sus.api.controllers;

import fiap.sus.api.dto.unidade.UnidadeRequest;
import fiap.sus.api.dto.unidade.UnidadeResponse;
import fiap.sus.api.mappers.EspecialidadeDomainMapper;
import fiap.sus.api.mappers.UnidadeMapper;
import fiap.sus.application.usecases.unidades.AtualizaUnidadeUseCase;
import fiap.sus.application.usecases.unidades.BuscaUnidadesUseCase;
import fiap.sus.application.usecases.unidades.DeletaUnidadeUseCase;
import fiap.sus.application.usecases.unidades.SalvaUnidadeUseCase;
import fiap.sus.domain.model.UnidadeDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/unidades")
@RequiredArgsConstructor
public class UnidadeController {

    private final SalvaUnidadeUseCase salvaUnidadeUseCase;
    private final BuscaUnidadesUseCase buscaUnidadesUseCase;
    private final DeletaUnidadeUseCase deletaUnidadeUseCase;
    private final AtualizaUnidadeUseCase atualizaUnidade;
    private final UnidadeMapper unidadeMapper;
    private final EspecialidadeDomainMapper especialidadeDomainMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvaUnidade(@RequestBody UnidadeRequest request) {

        log.info("Salvando unidade: {}", request.nome());

        var unidade = new UnidadeDomain(request.id(), request.nome(), request.endereco(),
                especialidadeDomainMapper.toDomainList(request.especialidades()),
                request.ativo());
        log.info("Unidade Salva com sucesso: {}", unidade.getNome());
        salvaUnidadeUseCase.save(unidade);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaUnidade(@PathVariable Long id) {
        log.info("Deletando unidade com id: [{}]", id);

        buscaUnidadesUseCase.findById(id).ifPresentOrElse(
                unidade -> deletaUnidadeUseCase.deleteById(id),
                () -> log.warn("Unidade não encontrada com id: [{}]", id)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDomain> buscaUnidadePorId(@PathVariable Long id) {

        return buscaUnidadesUseCase.findById(id).map(
                        ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<UnidadeDomain> buscaListaUnidades() {
        return buscaUnidadesUseCase.findAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UnidadeResponse atualizaUnidade(@RequestBody UnidadeRequest request) {
        log.info("Atualizando unidade: [{}]", request.nome());
        var unidade = atualizaUnidade.update(request.id(), request.nome(), request.endereco(), request.ativo());
        log.info("Unidade atualizada com sucesso: [{}]", request.nome());

        return unidadeMapper.toResponse(unidade);
    }

    //TODO
//    //Pegar lista de medicos atendendo por unidade
//    @GetMapping("medicos-por-unidade/{IdUnidade}")
//    public List<UnidadeDomain> buscaMedicosPorUnidade(@PathVariable Long IdUnidade) {
//        return List.of(new UnidadeDomain(1L, "Unidade 1", "Rua A", true),
//                new UnidadeDomain(2L, "Unidade 2", "Rua B", true));
//    }

}
