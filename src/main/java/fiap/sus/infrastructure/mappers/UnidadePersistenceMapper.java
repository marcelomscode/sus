package fiap.sus.infrastructure.mappers;


import fiap.sus.domain.model.EspecialidadesDomain;
import fiap.sus.domain.model.UnidadeDomain;
import fiap.sus.infrastructure.persistence.EspecialidadesPersistence;
import fiap.sus.infrastructure.persistence.UnidadePersistence;

import java.util.Set;
import java.util.stream.Collectors;

public class UnidadePersistenceMapper {

    public static Set<EspecialidadesPersistence> toPersistenceList(Set<EspecialidadesDomain> especialidadeDomain){

        if (especialidadeDomain == null) {
            return null;
        }

        return especialidadeDomain.stream()
                .map(domain -> EspecialidadesPersistence.builder()
                        .id(domain.getId())
                        .nome(domain.getNome())
                        .descricao(domain.getDescricao())
                        .build())
                .collect(Collectors.toSet());

    }

    public static Set<EspecialidadesDomain> toDomainList(Set<EspecialidadesPersistence> especialidadesPersistences){

        if(especialidadesPersistences == null){
            return null;
        }

        return especialidadesPersistences.stream()
                .map(persistence -> EspecialidadesDomain.builder()
                        .id(persistence.getId())
                        .nome(persistence.getNome())
                        .descricao(persistence.getDescricao())
                        .build())
        .collect(Collectors.toSet());
    }

    public static UnidadeDomain toDomain(UnidadePersistence persistence){
        if(persistence == null){
            return null;
        }

        return new UnidadeDomain(
                persistence.getId(),
                persistence.getNome(),
                persistence.getEndereco(),
                toDomainList(persistence.getEspecialidades()),
                persistence.isAtivo());
    }

}
