package fiap.sus.api.mappers;

import fiap.sus.api.dto.especialidade.EspecialidadeRequest;
import fiap.sus.api.dto.especialidade.EspecialidadeResponse;
import fiap.sus.api.dto.especialidade.NovaEspecialidadeRequest;
import fiap.sus.domain.model.EspecialidadesDomain;

public class EspecialidadeMapperOld {

    public static EspecialidadesDomain toDomain(EspecialidadeRequest especialidadeResponse) {
        return EspecialidadesDomain
                .builder()
                .id(especialidadeResponse.getId())
                .nome(especialidadeResponse.getNome())
                .descricao(especialidadeResponse.getDescricao())
                .build();
    }

    public static EspecialidadesDomain toDomainNovaEspecialidade(NovaEspecialidadeRequest especialidadeResponse) {
        return EspecialidadesDomain
                .builder()
                .nome(especialidadeResponse.getNome())
                .descricao(especialidadeResponse.getDescricao())
                .build();
    }


    public static EspecialidadeResponse toResponse(EspecialidadesDomain especialidadesDomain) {
        return new EspecialidadeResponse(
                especialidadesDomain.getId(),
                especialidadesDomain.getNome(),
                especialidadesDomain.getDescricao()
        );
    }


}
