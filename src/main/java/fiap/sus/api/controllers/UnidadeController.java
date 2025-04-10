package fiap.sus.api.controllers;

import fiap.sus.api.dto.unidade.MedicosAtendendoUnidadeResponse;
import fiap.sus.api.dto.unidade.UnidadeRequest;
import fiap.sus.api.dto.unidade.UnidadeResponse;
import fiap.sus.api.mappers.EspecialidadeDomainMapper;
import fiap.sus.api.mappers.UnidadeDomainMapper;
import fiap.sus.application.usecases.unidades.AtualizaUnidadeUseCase;
import fiap.sus.application.usecases.unidades.BuscaMedicosAtendendoNaUnidadeUseCase;
import fiap.sus.application.usecases.unidades.BuscaUnidadesUseCase;
import fiap.sus.application.usecases.unidades.DeletaUnidadeUseCase;
import fiap.sus.application.usecases.unidades.SalvaUnidadeUseCase;
import fiap.sus.domain.exceptions.DominioException;
import fiap.sus.domain.model.UnidadeDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.websocket.server.PathParam;
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

import java.time.LocalDate;
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
    private final UnidadeDomainMapper unidadeDomainMapper;
    private final EspecialidadeDomainMapper especialidadeDomainMapper;
    private final BuscaMedicosAtendendoNaUnidadeUseCase buscaMedicosAtendendoNaUnidadeUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salva uma nova unidade com uma ou mais especialidades.",
            description = "Salva uma nova unidade com uma ou mais especialidades através do id da especialidade.")
    @Schema(name = "UnidadeRequest", implementation = UnidadeRequest.class)
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
    @Operation(summary = "Lista todas as unidades.",description = "Lista todas as unidades.")
    public List<UnidadeDomain> buscaListaUnidades() {
        return buscaUnidadesUseCase.findAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualiza unidade.",description = "Atualiza unidade.")
    public UnidadeResponse atualizaUnidade(@RequestBody UnidadeRequest request) {
        log.info("Atualizando unidade: [{}]", request.nome());
        var unidade = atualizaUnidade.update(request.id(), request.nome(), request.endereco(), request.ativo());
        log.info("Unidade atualizada com sucesso: [{}]", request.nome());

        return unidadeDomainMapper.toResponse(unidade);
    }

    @GetMapping("/medicos/{idUnidade}/checkin")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista médicos atendendo na unidade por data.",description = "Lista médicos atendendo na unidade por data.")
    @ApiResponse(responseCode = "200", description = "Lista de Médicos atendendo no unidade.",content = @Content(schema = @Schema(implementation = MedicosAtendendoUnidadeResponse.class)))
    @ApiResponse(responseCode = "404", description = "Nenhuma médico atendendo no momento.",content = @Content(schema = @Schema(implementation = DominioException.class)))
    public ResponseEntity<MedicosAtendendoUnidadeResponse>
    buscaMedicosPorUnidade(@PathVariable Long idUnidade,
                           @Parameter(name = "data", description = "dd/mm/yyyy")
                           @PathParam("data") LocalDate data) {
        log.info("Buscando médicos atendendo na unidade: [{}]", idUnidade);
        return ResponseEntity.ok(buscaMedicosAtendendoNaUnidadeUseCase.buscaMedicosAtendendoNaUnidade(idUnidade, data));
    }

}
