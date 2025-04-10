package fiap.sus.api.dto.unidade;

import fiap.sus.api.dto.especialidade.EspecialidadeResponse;

import java.util.Set;

public record UnidadeResponse(
        Long id,
        String nome,
        String endereco,
        boolean ativo,
        Set<EspecialidadeResponse> especialidades
) {}
