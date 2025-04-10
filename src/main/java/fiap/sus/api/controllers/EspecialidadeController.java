package fiap.sus.api.controllers;

import fiap.sus.api.dto.especialidade.EspecialidadeRequest;
import fiap.sus.api.dto.especialidade.EspecialidadeResponse;
import fiap.sus.api.dto.especialidade.NovaEspecialidadeRequest;
import fiap.sus.api.mappers.EspecialidadeMapperOld;
import fiap.sus.application.usecases.especialidades.AtualizaEspecialidadeUseCase;
import fiap.sus.application.usecases.especialidades.BuscaEspecialidadesUseCase;
import fiap.sus.application.usecases.especialidades.SalvaEspecialidadeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/especialidades")
@RequiredArgsConstructor
public class EspecialidadeController {

    private final SalvaEspecialidadeUseCase salvaEspecialidadeUseCase;
    private final AtualizaEspecialidadeUseCase atualizaEspecialidadeUseCase;
    private final BuscaEspecialidadesUseCase buscaEspecialidadeUseCase;

    @GetMapping
    @Operation(summary = "Lista especialidades.", description = "Lista todas as especialidades cadastradas e disponíveis.")
    @ApiResponse(responseCode = "200", description = "Listagem de todas as unidades")
    @Schema(name = "EspecialidadeResponse", implementation = EspecialidadeResponse.class)
    public ResponseEntity<List<EspecialidadeResponse>> listarTodasAsEspecialidades() {

        var especialidades = buscaEspecialidadeUseCase.listarTodasEspecialidades().stream().map(
                EspecialidadeMapperOld::toResponse).toList();
        log.info("FIM Listando todas as especialidades");

        return ResponseEntity.ok(especialidades);
    }

    @PostMapping
    @Operation(summary = "Salvar nova especialidade.", description = "Opção para salvar uma nova Especialidade para ser usada nas Unidades.")
    @ApiResponse(responseCode = "200", description = "Especialidade salva com sucesso")
    @Schema(name = "NovaEspecialidadeRequest", implementation = NovaEspecialidadeRequest.class)
    public ResponseEntity<String> salvaEspecialidade(
            @Parameter(description = "Id de um entregador ja cadastrado para entregas."
                    , required = true)
            @RequestBody NovaEspecialidadeRequest especialidadeRequest ) {

        salvaEspecialidadeUseCase.save(EspecialidadeMapperOld.toDomainNovaEspecialidade(especialidadeRequest));
        log.info("FIM salva especialidade: {}", especialidadeRequest);

        return ResponseEntity.ok("Especialidade salva com sucesso");
    }

    @PutMapping
    @Operation(summary = "Atualiza uma especialidade cadastrada.", description = "Opção para atualizar uma especialidade.")
    @ApiResponse(responseCode = "200", description = "Especialidade atualizada com sucesso")
    @Schema(name = "EspecialidadeRequest", implementation = EspecialidadeRequest.class)
    public ResponseEntity<EspecialidadeResponse> atualizarEspecialidade(@RequestBody EspecialidadeRequest especialidadeRequest) {
        var especialidadeDomain = EspecialidadeMapperOld.toDomain(especialidadeRequest);
        var especialidadeAtualizada = atualizaEspecialidadeUseCase.atualizar(especialidadeDomain);

        log.info("FIM Atualizando especialidade: {}", especialidadeAtualizada);
        return ResponseEntity.ok(EspecialidadeMapperOld.toResponse(especialidadeAtualizada));
    }

}
