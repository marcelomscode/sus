package fiap.sus.api.dto.unidade;

import fiap.sus.api.dto.especialidade.EspecialidadeResponse;
import fiap.sus.domain.model.UnidadeDomain;

import java.util.Set;
import java.util.stream.Collectors;

public record UnidadeResponse(
        Long id,
        String nome,
        String endereco,
        boolean ativo,
        Set<EspecialidadeResponse> especialidades
) {
    // Método para converter Domain -> Response
    public static UnidadeResponse fromDomain(UnidadeDomain domain) {
        return new UnidadeResponse(
                domain.getId(),
                domain.getNome(),
                domain.getEndereco(),
                domain.isAtivo(),
                domain.getEspecialidades().stream()
                        .map(EspecialidadeResponse::fromDomain)
                        .collect(Collectors.toSet())
        );
    }
}
