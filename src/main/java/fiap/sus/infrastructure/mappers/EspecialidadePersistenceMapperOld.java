package fiap.sus.infrastructure.mappers;

import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.infrastructure.persistence.EspecialidadesPersistence;

public class EspecialidadePersistenceMapperOld {

    public static EspecialidadesDomain toDomain(EspecialidadesPersistence especialidadePersistence) {
        return new EspecialidadesDomain(
                especialidadePersistence.getId(),
                especialidadePersistence.getNome(),
                especialidadePersistence.getDescricao()
        );
    }

    public static EspecialidadesPersistence toPersistence(EspecialidadesDomain especialidadesDomain) {
        return new EspecialidadesPersistence(
                especialidadesDomain.getId(),
                especialidadesDomain.getNome(),
                especialidadesDomain.getDescricao()
        );
    }


}
