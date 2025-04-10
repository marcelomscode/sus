package fiap.sus.api.dto.especialidade;

import fiap.sus.domain.model.EspecialidadesDomain;

public record EspecialidadeResponse(Long id, String nome, String descricao) {

    public static EspecialidadeResponse fromDomain(EspecialidadesDomain domain) {
        return new EspecialidadeResponse(domain.getId(), domain.getNome(), domain.getDescricao());
    }
}
