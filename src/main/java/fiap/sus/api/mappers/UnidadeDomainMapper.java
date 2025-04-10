package fiap.sus.api.mappers;

import fiap.sus.api.dto.unidade.UnidadeResponse;
import fiap.sus.domain.model.UnidadeDomain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UnidadeDomainMapper {

    UnidadeDomainMapper INSTANCE = Mappers.getMapper(UnidadeDomainMapper.class);

    UnidadeResponse toResponse(UnidadeDomain domain);

}
