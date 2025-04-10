package fiap.sus.infrastructure.mappers;

import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.infrastructure.persistence.EspecialidadesPersistence;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface EspecialidadePersistenceMapper {

    EspecialidadePersistenceMapper INSTANCE = Mappers.getMapper(EspecialidadePersistenceMapper.class);

    Set<EspecialidadesPersistence> toPersistence(Set<EspecialidadesDomain> domain);


}
