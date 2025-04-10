package fiap.sus.api.mappers;

import fiap.sus.api.dto.especialidade.EspecialidadeUnidadeRequest;
import fiap.sus.domain.model.EspecialidadesDomain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface EspecialidadeDomainMapper {

    EspecialidadeDomainMapper INSTANCE = Mappers.getMapper(EspecialidadeDomainMapper.class);

    EspecialidadesDomain toDomain(EspecialidadeUnidadeRequest especialidade);

    Set<EspecialidadesDomain> toDomainList(Set<EspecialidadeUnidadeRequest> especialidade);

}
