package fiap.sus.infrastructure.mappers;

import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.infrastructure.persistence.UnidadePersistence;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UnidadePersistenceMapper {

    UnidadePersistenceMapper INSTANCE = Mappers.getMapper(UnidadePersistenceMapper.class);

    UnidadePersistence toPersistence(UnidadeDomain domain);
    UnidadeDomain toDomain(UnidadePersistence persistence);

}
